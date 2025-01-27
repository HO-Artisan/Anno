package ho.artisan.anno.datagen.provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.data.DataCache;
import net.minecraft.data.DataProvider;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public final class AnnoLangProvider implements DataProvider  {
    private final Map<String, String> map;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final FabricDataGenerator generator;
    private final String languageCode;
    private final String modid;

    public AnnoLangProvider(FabricDataGenerator generator, String languageCode, String modid, Map<String, String> map) {
        this.map = map;
        this.generator = generator;
        this.languageCode = languageCode;
        this.modid = modid;
    }

    public void generateTranslations(FabricLanguageProvider.TranslationBuilder translationBuilder) {
        map.forEach(translationBuilder::add);
    }

    @Override
    public String getName() {
        return "Anno Language[" + languageCode + "]";
    }

    @Override
    public void run(DataCache cache) throws IOException {
        TreeMap<String, String> translationEntries = new TreeMap<>();

        generateTranslations((String key, String value) -> {
            Objects.requireNonNull(key);
            Objects.requireNonNull(value);

            if (translationEntries.containsKey(key)) {
                throw new RuntimeException("Existing translation key found - " + key + " - Duplicate will be ignored.");
            }

            translationEntries.put(key, value);
        });

        JsonObject langEntryJson = new JsonObject();

        for (Map.Entry<String, String> entry : translationEntries.entrySet()) {
            langEntryJson.addProperty(entry.getKey(), entry.getValue());
        }

        DataProvider.writeToPath(GSON, cache, langEntryJson, getLangFilePath(this.languageCode));
    }

    private Path getLangFilePath(String code) {
        return generator.getOutput().resolve("assets/%s/lang/%s.json".formatted(modid, code));
    }
}
