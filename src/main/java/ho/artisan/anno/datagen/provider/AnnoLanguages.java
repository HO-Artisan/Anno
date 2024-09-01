package ho.artisan.anno.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnoLanguages extends FabricLanguageProvider {
    private final Map<String, String> map;
    private final String name;
    private final String langCode;

    public AnnoLanguages(FabricDataGenerator generator, String langCode, Dictionary dictionary, String name) {
        super(generator, langCode);
        this.map = dictionary.get(langCode);
        this.name = name;
        this.langCode = langCode;
    }

    @Override
    public void generateTranslations(TranslationBuilder builder) {
        map.forEach(builder::add);
    }

    @Override
    public String getName() {
        return "Language/" + name + "/" + langCode;
    }

    public static class Dictionary {
        private final Map<String, Map<String, String>> langMap = new HashMap<>();

        public void add(String languageCode, String key, String text) {
            String langCode = languageCode.toLowerCase();
            if (langMap.containsKey(langCode)) {
                langMap.get(langCode).put(key, text);
            }else {
                HashMap<String, String> map = new HashMap<>();
                map.put(key, text);
                langMap.put(langCode, map);
            }
        }

        public Map<String, String> get(String langCode) {
            return langMap.getOrDefault(langCode, new HashMap<>());
        }

        public Set<String> langCodes() {
            return langMap.keySet();
        }
    }
}
