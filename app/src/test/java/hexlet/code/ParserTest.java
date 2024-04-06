package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {

    @Test
    void testReadJsonFile() {
        Map<String, Object> actual;
        try {
            actual = Parser.readFile("src/test/resources/json/testFile3.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Map<String, Object> expected = new HashMap<>();
        expected.put("host", "hexlet.io");
        expected.put("timeout", 50);
        expected.put("proxy", "123.234.53.22");
        expected.put("follow", false);

        assertEquals(expected, actual);
    }

    @Test
    void testReadUnsupportedExtensionFile() {
        assertThrows(IOException.class, () -> Parser.readFile("src/test/resources/txt/testFile.txt"));
    }
}
