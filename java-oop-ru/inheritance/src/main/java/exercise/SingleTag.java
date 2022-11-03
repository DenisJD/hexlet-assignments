package exercise;

import java.util.Map;

// BEGIN
class SingleTag extends Tag {

    public SingleTag(String pName, Map<String, String> pAttributes) {
        super(pName, pAttributes);
    }

    @Override
    public String toString() {
        return "<"
                + this.name
                + getAttribute(this.attributes)
                + ">";
    }
}
// END
