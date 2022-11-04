package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {
    public static List<String> validate(Address address) {
        List<String> result = new ArrayList<>();
        Field[] fieldsOfClass = address.getClass().getDeclaredFields();
        for (Field field : fieldsOfClass) {
            NotNull notNull = field.getAnnotation(NotNull.class);
            if (notNull != null) {
                try {
                    field.setAccessible(true);
                    String valueOfField = (String) field.get(address);
                    if (valueOfField == null) {
                        result.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static Map<String, List<String>> advancedValidate(Address address) throws NullPointerException {
        Map<String, List<String>> result = new LinkedHashMap<>();
        Field[] fieldsOfClass = address.getClass().getDeclaredFields();
        for (Field field : fieldsOfClass) {
            NotNull notNull = field.getAnnotation(NotNull.class);
            MinLength minLength = field.getAnnotation(MinLength.class);
            List<String> errorMessage = new ArrayList<>();
            try {
                field.setAccessible(true);
                String valueOfField = (String) field.get(address);
                if (notNull != null || minLength != null) {
                    if (valueOfField == null) {
                        String messageNull = "can not be null";
                        errorMessage.add(messageNull);
                    } else if (minLength != null && valueOfField.length() < minLength.minLength()) {
                        String messageLength = "length less than " + minLength.minLength();
                        errorMessage.add(messageLength);
                    }
                }
                if (!errorMessage.isEmpty()) {
                    result.put(field.getName(), errorMessage);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
// END
