package exercise;

import java.util.LinkedHashMap;
import java.util.Map;

// BEGIN
class Tag {
    String name;
    Map<String, String> attributes = new LinkedHashMap<>();

    public Tag(String pName, Map<String, String> pAttributes) {
        this.name = pName;
        this.attributes.putAll(pAttributes);
    }

    public String getAttribute(Map<String, String> map) {
        StringBuilder result = new StringBuilder();
        map.keySet()
                .forEach(k -> result
                        .append(" ")
                        .append(k)
                        .append("=\"")
                        .append(map.get(k))
                        .append("\""));
        return result.toString();
    }
}
// END
