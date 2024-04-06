package hexlet.code.formatters;

import java.util.Map;
import java.util.Set;

public class Plain {
    public static String format(Map<String, Object> map1, Map<String, Object> map2, Set<String> keys) {
        StringBuilder result = new StringBuilder();
        for (String key : keys) {
            addComparePlain(result, map1, map2, key);
        }
        return result.substring(0, result.lastIndexOf("\n"));
    }


    private static void addComparePlain(
            StringBuilder result,
            Map<String, Object> map1,
            Map<String, Object> map2,
            String key
    ) {
        boolean isPresentInMap1 = map1.containsKey(key);
        boolean isPresentInMap2 = map2.containsKey(key);

        Object value1 = map1.get(key);
        Object value2 = map2.get(key);

        if (isPresentInMap1 != isPresentInMap2) {
            if (isPresentInMap1) {
                result.append("Property '").append(key).append("' was removed\n");
            } else {
                result.append("Property '").append(key).append("' was added with value: ")
                        .append(getFormattedValue(map2, key)).append("\n");
            }
            return;
        }

        if (value1 == null && value2 == null) {
            return;
        }
        if (value1 == null || value2 == null) {
            result.append("Property '").append(key).append("' was updated. From ").append(getFormattedValue(map1, key))
                    .append(" to ").append(getFormattedValue(map2, key)).append("\n");
            return;
        }

        if (!value1.equals(value2)) {
            result.append("Property '").append(key).append("' was updated. From ").append(getFormattedValue(map1, key))
                    .append(" to ").append(getFormattedValue(map2, key)).append("\n");
        }
    }

    public static String getFormattedValue(Map<String, Object> map, String key) {
        Object value = map.get(key);
        if (value == null) {
            return null;
        }
        if (value.getClass().equals(String.class)) {
            return "'" + value + "'";
        }
        return (isSimple(value.getClass())) ? value.toString() : "[complex value]";
    }

    private static boolean isSimple(Class<?> checkedClass) {
        return checkedClass.equals(Boolean.class) || checkedClass.equals(Integer.class);
    }
}
