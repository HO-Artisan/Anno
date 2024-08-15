package ho.artisan.anno.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class AnnoLanguageProvider extends FabricLanguageProvider {
    private final Map<String, String> map;
    private final String name;

    public AnnoLanguageProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> lookup, String languageCode, String name , Map<String, String> map) {
        super(output, languageCode, lookup);
        this.map = map;
        this.name = name + "/" + languageCode;
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder builder) {
        map.forEach(builder::add);
    }

    @Override
    public String getName() {
        return name;
    }
}
