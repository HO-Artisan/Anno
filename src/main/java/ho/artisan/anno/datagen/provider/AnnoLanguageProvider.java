package ho.artisan.anno.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import java.util.Map;

public class AnnoLanguageProvider extends FabricLanguageProvider {
    private final Map<String, String > map;

    public AnnoLanguageProvider(FabricDataGenerator generator, String languageCode, Map<String, String> map) {
        super(generator, languageCode);
        this.map = map;
    }

    @Override
    public void generateTranslations(TranslationBuilder builder) {
        map.forEach(builder::add);
    }

    @Override
    public String getName() {
        return "Anno Language";
    }
}
