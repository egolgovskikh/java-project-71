package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static hexlet.code.StringUtils.addInString;

public class Differ {
    public static String generate(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());

        StringBuilder result = new StringBuilder("{");
        for (String key : keys) {
            boolean isPresentInMap1 = map1.containsKey(key);
            boolean isPresentInMap2 = map2.containsKey(key);

            if (isPresentInMap1 && isPresentInMap2) {
                boolean isValuesEquals = map1.get(key).equals(map2.get(key));
                if (isValuesEquals) {
                    addInString(result, ' ', key, map1.get(key));
                } else {
                    addInString(result, '-', key, map1.get(key));
                    addInString(result, '+', key, map2.get(key));
                }
            }
            if (!isPresentInMap1 && isPresentInMap2) {
                addInString(result, '+', key, map2.get(key));
            }
            if (isPresentInMap1 && !isPresentInMap2) {
                addInString(result, '-', key, map1.get(key));
            }
        }
        result.append("\n}");

        return result.toString();
    }


}
