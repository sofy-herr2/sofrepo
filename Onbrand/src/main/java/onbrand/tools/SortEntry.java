package onbrand.tools;


import java.util.HashMap;
import java.util.Map;

public class SortEntry {
    public Map<String, String> attributes = new HashMap<>();

    public void setAttribute(String attributeName, String attributeValue) {
        attributes.put(attributeName, attributeValue);
    }

    public String getAttribute(String attributeName) {
        return attributes.get(attributeName);
    }
}

