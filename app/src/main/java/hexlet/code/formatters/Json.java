package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Json {
    public static String format(
            Map<String, Object> map1,
            Map<String, Object> map2,
            Set<String> keys
    ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> comparisonResults = new HashMap<>();
        for (String key : keys) {
            addCompareJson(comparisonResults, map1, map2, key);
        }
        return objectMapper.writeValueAsString(comparisonResults);
    }

    private static void addCompareJson(
            Map<String, String> comparisonResults,
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
                comparisonResults.put(key, "removed");
            } else {
                comparisonResults.put(key, "added");
            }
            return;
        }

        if (value1 == null && value2 == null) {
            return;
        }
        if (value1 == null || value2 == null) {
            comparisonResults.put(key, "updated");
            return;
        }

        if (!value1.equals(value2)) {
            comparisonResults.put(key, "updated");
        }
    }
}
