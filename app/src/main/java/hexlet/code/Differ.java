package hexlet.code;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        Map<String, Object> map1 = Parser.readFile(filepath1);
        Map<String, Object> map2 = Parser.readFile(filepath2);

        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());

        StringBuilder result = new StringBuilder("{");
        for (String key : keys) {
            addCompare(result, map1, map2, key);
        }
        result.append("\n}");

        return result.toString();
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
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

        Object value1 = map1.get(key);
        Object value2 = map2.get(key);

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
