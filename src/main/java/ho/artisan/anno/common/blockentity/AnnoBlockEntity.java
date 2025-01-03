package ho.artisan.anno.common.blockentity;

import ho.artisan.anno.core.FieldEntry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class AnnoBlockEntity extends BlockEntity {
    public AnnoBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        for (Data data : FieldEntry.get(getClass(), this).stream().map(Data::new).toList()) {
            data.write(nbt::putBoolean, Boolean.class);
            data.write(nbt::putByte, Byte.class);
            data.write(nbt::putDouble, Double.class);
            data.write(nbt::putFloat, Float.class);
            data.write(nbt::putInt, Integer.class);
            data.write(nbt::putLong, Long.class);
            data.write(nbt::putShort, Short.class);
            data.write(nbt::putUuid, UUID.class);
            data.write(nbt::putByteArray, byte[].class);
            data.write(nbt::putIntArray, int[].class);
            data.write(nbt::putLongArray, long[].class);
        }
        if (this instanceof Inventory inventory) {
            NbtList nbtList = new NbtList();
            for (int i = 0; i < inventory.size(); i++) {
                ItemStack itemStack = inventory.getStack(i);
                if (!itemStack.isEmpty()) {
                    NbtCompound nbtCompound = new NbtCompound();
                    nbtCompound.putByte("Slot", (byte)i);
                    itemStack.writeNbt(nbtCompound);
                    nbtList.add(nbtCompound);
                }
            }
            nbt.put("Items", nbtList);
        }
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        for (Data data : FieldEntry.get(getClass(), this).stream().map(Data::new).toList()) {
            data.read(nbt::getBoolean, Boolean.class);
            data.read(nbt::getByte, Byte.class);
            data.read(nbt::getDouble, Double.class);
            data.read(nbt::getFloat, Float.class);
            data.read(nbt::getInt, Integer.class);
            data.read(nbt::getLong, Long.class);
            data.read(nbt::getShort, Short.class);
            data.read(nbt::getUuid, UUID.class);
            data.read(nbt::getByteArray, byte[].class);
            data.read(nbt::getIntArray, int[].class);
            data.read(nbt::getLongArray, long[].class);
        }
        if (this instanceof Inventory inventory) {
            NbtList nbtList = nbt.getList("Items", 10);

            for (int i = 0; i < nbtList.size(); i++) {
                NbtCompound nbtCompound = nbtList.getCompound(i);
                int j = nbtCompound.getByte("Slot") & 255;
                if (j < inventory.size()) {
                    inventory.setStack(j, ItemStack.fromNbt(nbtCompound));
                }
            }
        }
    }

    public record Data(FieldEntry entry) {
        public <T> void write(BiConsumer<String, T> consumer, Class<T> tClass) {
            if (entry.is(tClass))
                consumer.accept(entry.id(), entry.cast(tClass));
        }

        public <T> void read(Function<String, T> function, Class<T> tClass) {
            if (entry.is(tClass))
                entry.set(tClass.cast(function.apply(entry.id())));
        }
    }
}
