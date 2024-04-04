package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    @Test
    void testGenerate() {
        Map<String, Object> map1 = new HashMap<>();
        map1.put("host", "hexlet.io");
        map1.put("timeout", 50);
        map1.put("proxy", "123.234.53.22");
        map1.put("follow", false);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("timeout", 20);
        map2.put("verbose", true);
        map2.put("host", "hexlet.io");

        String actual = Differ.generate(map1, map2);
        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        assertEquals(expected, actual);
    }
}