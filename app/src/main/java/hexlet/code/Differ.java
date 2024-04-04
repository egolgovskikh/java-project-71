package hexlet.code;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        Map<String, Object> map1 = FileUtils.readFile(filepath1);
        Map<String, Object> map2 = FileUtils.readFile(filepath2);

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
        return generate(filepath1, filepath2, "plain");
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
