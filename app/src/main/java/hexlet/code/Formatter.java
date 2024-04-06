package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;
import java.util.Set;

public class Formatter {
    public static String format(
            Map<String, Object> map1,
            Map<String, Object> map2,
            Set<String> keys,
            String formatType
    ) throws JsonProcessingException {
        return switch (formatType) {
            case "stylish" -> Stylish.format(map1, map2, keys);
            case "plain" -> Plain.format(map1, map2, keys);
            case "json" -> Json.format(map1, map2, keys);
            default -> throw new IllegalStateException("Unexpected value: " + formatType);
        };
    }

}
