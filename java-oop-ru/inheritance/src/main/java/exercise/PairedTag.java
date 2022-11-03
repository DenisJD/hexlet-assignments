package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// BEGIN
class PairedTag extends Tag {
    String body;
    List<Tag> children = new ArrayList<>();

    public PairedTag(String pName, Map<String, String> pAttributes, String pBody, List<Tag> pChildren) {
        super(pName, pAttributes);
        this.body = pBody;
        this.children.addAll(pChildren);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result
                .append("<")
                .append(this.name)
                .append(getAttribute(this.attributes))
                .append(">")
                .append(this.body);
        children.forEach(result::append);
        result
                .append("</")
                .append(this.name)
                .append(">");
        return result.toString();
    }

}
// END
