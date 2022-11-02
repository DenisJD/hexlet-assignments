package exercise;

import java.util.Map;

// BEGIN
class App {

    public static void swapKeyValue(KeyValueStorage map) {
        String newKey;
        String newValue;
        for (Map.Entry<String, String> k : map.toMap().entrySet()) {
            newKey = k.getValue();
            newValue = k.getKey();
            map.unset(k.getKey());
            map.set(newKey, newValue);
        }
    }
}
// END
