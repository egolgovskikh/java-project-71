package hexlet.code.formatters;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PlainTest {

    @Test
    void testGetFormattedValueNull () {
        Map<String, Object> map = new HashMap<>();
        String key = "key";
        map.put(key, null);
        assertNull( Plain.getFormattedValue(map, key));
    }
}