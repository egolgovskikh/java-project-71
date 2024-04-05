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

        if (format.equals("stylish")) {
            StringBuilder result = new StringBuilder("{");
            for (String key : keys) {
                addCompareStylish(result, map1, map2, key);
            }
            result.append("\n}");
            return result.toString();
        }
        if (format.equals("plain")) {
            StringBuilder result = new StringBuilder();
            for (String key : keys) {
                addComparePlain(result, map1, map2, key);
            }
            return result.substring(0, result.lastIndexOf("\n"));
        }
        if (format.equals("json")) { //todo
            StringBuilder result = new StringBuilder("{");
            for (String key : keys) {
                addCompareStylish(result, map1, map2, key);
            }
            result.append("\n}");
            return result.toString();
        }
        throw new IOException("Unsupported format: " + format);
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
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
                result.append("Property '").append(key).append("' was added with value: ").append(getFormattedValue(map2, key)).append("\n");
            }
            return;
        }

        if (value1 == null && value2 == null) {
            return;
        }
        if (value1 == null || value2 == null) {
            result.append("Property '").append(key).append("' was updated. From ").append(getFormattedValue(map1, key)).append(" to ")
                    .append(getFormattedValue(map2, key)).append("\n");
            return;
        }

        if (!value1.equals(value2)) {
            result.append("Property '").append(key).append("' was updated. From ").append(getFormattedValue(map1, key)).append(" to ")
                    .append(getFormattedValue(map2, key)).append("\n");
        }
    }

    private static void addCompareStylish(
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

    private static String getFormattedValue(Map<String, Object> map, String key) {
        if (map.get(key) == null) {
            return null;
        }
        if (map.get(key).getClass().equals(String.class)) {
            return "'" + map.get(key) + "'";
        }
        return (isSimple(map.get(key).getClass())) ? map.get(key).toString() : "[complex value]";
    }

    private static boolean isSimple(Class<?> checkedClass) {
        return checkedClass.equals(Boolean.class) || checkedClass.equals(Integer.class);
    }

}
