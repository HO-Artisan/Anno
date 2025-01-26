package ho.artisan.anno.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import java.util.Map;

public final class AnnoLangProvider extends FabricLanguageProvider {
    private final Map<String, String> map;

    public AnnoLangProvider(FabricDataGenerator generator, String languageCode, Map<String, String> map) {
        super(generator, languageCode);
        this.map = map;
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        map.forEach(translationBuilder::add);
    }

    @Override
    public String getName() {
        return "Anno " + super.getName();
    }
}
