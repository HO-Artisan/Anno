package ho.artisan.anno;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnnoMod implements ModInitializer {
    public static final String MOD_ID = "anno";
    public static final String MOD_NAME = "Anno";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    @Override
    public void onInitialize() {
        AnnoCore.INSTANCE.load();
    }

    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }
}
