package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class FileKV implements KeyValueStorage {
    private final String path;
    private Map<String, String> map;

    FileKV(String pPath, Map<String, String> pMap) {
        this.path = pPath;
        this.map = new HashMap<>();
        this.map.putAll(pMap);
        Utils.writeFile(path, Utils.serialize(this.map));
    }

    @Override
    public void set(String key, String value) {
        this.map = Utils.unserialize(Utils.readFile(path));
        this.map.put(key, value);
        Utils.writeFile(path, Utils.serialize(this.map));
    }

    @Override
    public void unset(String key) {
        this.map = Utils.unserialize(Utils.readFile(path));
        this.map.remove(key);
        Utils.writeFile(path, Utils.serialize(this.map));
    }

    @Override
    public String get(String key, String defaultValue) {
        this.map = Utils.unserialize(Utils.readFile(path));
        return this.map.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        this.map = Utils.unserialize(Utils.readFile(path));
        return new HashMap<>(this.map);
    }
}
// END
