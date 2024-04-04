package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());

        StringBuilder result = new StringBuilder("{");
        for (String key : keys) {
            addCompare(result, map1, map2, key);
        }
        result.append("\n}");

        return result.toString();
    }

    private static void addCompare(
            StringBuilder result,
            Map<String, Object> map1,
            Map<String, Object> map2,
            String key
    ) {
        boolean isPresentInMap1 = map1.containsKey(key);
        boolean isPresentInMap2 = map2.containsKey(key);

        if (isPresentInMap1 != isPresentInMap2) {
            addString(result, isPresentInMap1 ? '-' : '+', key, isPresentInMap1 ? map1.get(key) : map2.get(key));
            return;
        }

        if (map1.get(key).equals(map2.get(key))) {
            addString(result, ' ', key, map1.get(key));
        } else {
            addString(result, '-', key, map1.get(key));
            addString(result, '+', key, map2.get(key));
        }
    }

    private static void addString(StringBuilder str, char sign, String key, Object value) {
        str.append("\n  ").append(sign).append(" ").append(key).append(": ").append(value);
    }
}
