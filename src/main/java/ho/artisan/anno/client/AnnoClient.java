package ho.artisan.anno.client;

import ho.artisan.anno.AnnoResolvers;
import ho.artisan.anno.example.AnnoRegistration;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class AnnoClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            AnnoResolvers.clientResolve(AnnoRegistration.class);
        }
    }
}
