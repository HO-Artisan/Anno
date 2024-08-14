package ho.artisan.anno;

import ho.artisan.anno.example.AnnoRegistration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

public class Anno implements ModInitializer {
    public static final String MOD_ID = "anno";

    @Override
    public void onInitialize() {
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            AnnoResolvers.resolve(AnnoRegistration.class);
        }
    }

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}
