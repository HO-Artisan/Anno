package ho.artisan.anno.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import java.util.Map;

public class AnnoLanguageProvider extends FabricLanguageProvider {
    private final Map<String, String > map;

    public AnnoLanguageProvider(FabricDataOutput output, String languageCode, Map<String, String> map) {
        super(output, languageCode);
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
