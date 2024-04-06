package hexlet.code;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static hexlet.code.Formatter.format;

public class Differ {
    public static String generate(String filepath1, String filepath2, String formatType) throws IOException {
        Map<String, Object> map1 = Parser.readFile(filepath1);
        Map<String, Object> map2 = Parser.readFile(filepath2);

        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());

        return format(map1, map2, keys, formatType);
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }
}
