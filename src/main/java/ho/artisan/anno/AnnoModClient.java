package ho.artisan.anno;

import net.fabricmc.api.ClientModInitializer;

public class AnnoModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        AnnoCore.INSTANCE.clientLoad();
    }
}
