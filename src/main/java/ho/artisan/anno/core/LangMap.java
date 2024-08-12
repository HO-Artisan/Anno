package ho.artisan.anno.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LangMap {
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
