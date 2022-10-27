package exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

// BEGIN
class App {
    public static LinkedHashMap<String, String> genDiff(Map<String, Object> data1, Map<String, Object> data2) {
        Map<String, Object> jointMaps = new TreeMap<>();
        LinkedHashMap<String, String> result = new LinkedHashMap<>();
        jointMaps.putAll(data1);
        jointMaps.putAll(data2);
        for (Map.Entry<String, Object> maps : jointMaps.entrySet()) {
            if (!data1.containsKey(maps.getKey())) {
                result.put(maps.getKey(), "added");
            } else {
                result.put(maps.getKey(), "deleted");
            }
            if (data1.containsKey(maps.getKey()) && data2.containsKey(maps.getKey())) {
                if (data1.get(maps.getKey()).equals(data2.get(maps.getKey()))) {
                    result.put(maps.getKey(), "unchanged");
                } else {
                    result.put(maps.getKey(), "changed");
                }
            }
        }
        return result;
    }
}
//END
