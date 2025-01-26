package ho.artisan.anno.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class LangMap {
    private final Map<String, Map<String, String>> map;

    public LangMap() {
        this.map = new HashMap<>();
    }

    public void add(String code, String key, String value) {
        if (map.containsKey(code)) {
            map.get(code).put(key, value);
        } else {
            Map<String, String> subMap = new HashMap<>();
            subMap.put(key, value);
            map.put(code, subMap);
        }
    }

    public Map<String, String> get(String code) {
        return map.get(code);
    }

    public Set<String> codes() {
        return map.keySet();
    }
}
