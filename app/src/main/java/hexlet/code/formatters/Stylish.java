package hexlet.code.formatters;

import java.util.Map;
import java.util.Set;

public class Stylish {
    public static String format(Map<String, Object> map1, Map<String, Object> map2, Set<String> keys) {
        StringBuilder result = new StringBuilder("{");
        for (String key : keys) {
            addCompareStylish(map1, map2, key, result);
        }
        result.append("\n}");
        return result.toString();
    }

    private static void addCompareStylish(
            Map<String, Object> map1, Map<String, Object> map2, String key, StringBuilder result
    ) {
        boolean isPresentInMap1 = map1.containsKey(key);
        boolean isPresentInMap2 = map2.containsKey(key);

        Object value1 = map1.get(key);
        Object value2 = map2.get(key);

        if (isPresentInMap1 != isPresentInMap2) {
            addString(result, isPresentInMap1 ? '-' : '+', key, isPresentInMap1 ? value1 : value2);
            return;
        }

        if (value1 == null && value2 == null) {
            addString(result, ' ', key, null);
            return;
        }
        if (value1 == null || value2 == null) {
            addString(result, '-', key, value1);
            addString(result, '+', key, value2);
            return;
        }

        if (value1.equals(value2)) {
            addString(result, ' ', key, value1);
        } else {
            addString(result, '-', key, value1);
            addString(result, '+', key, value2);
        }
    }

    private static void addString(StringBuilder str, char sign, String key, Object value) {
        str.append("\n  ").append(sign).append(" ").append(key).append(": ").append(value);
    }
}
