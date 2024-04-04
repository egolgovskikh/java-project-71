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
            boolean isPresentInMap1 = map1.containsKey(key);
            boolean isPresentInMap2 = map2.containsKey(key);

            if (isPresentInMap1 && isPresentInMap2) {
                if (map1.get(key).equals(map2.get(key))) {
                    result.append("\n    ").append(key).append(": ").append(map1.get(key));
                } else {
                    result.append("\n  - ").append(key).append(": ").append(map1.get(key));
                    result.append("\n  + ").append(key).append(": ").append(map2.get(key));
                }
            }
            if (!isPresentInMap1 && isPresentInMap2) {
                result.append("\n  + ").append(key).append(": ").append(map2.get(key));
            }
            if (isPresentInMap1 && !isPresentInMap2) {
                result.append("\n  - ").append(key).append(": ").append(map1.get(key));
            }
        }
        result.append("\n}");

        return result.toString();
    }
}
