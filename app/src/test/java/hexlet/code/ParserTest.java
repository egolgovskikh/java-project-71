package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {
    private final String resourceDirectory = Paths.get("src", "test", "resources").toString();

    @Test
    void testReadJsonFile() {
        Map<String, Object> actual;
        try {

            actual = Parser.readFile(Paths.get(resourceDirectory, "testFile3.json").toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Map<String, Object> expected = new HashMap<>();
        int intValue = 50;
        expected.put("host", "hexlet.io");
        expected.put("timeout", intValue);
        expected.put("proxy", "123.234.53.22");
        expected.put("follow", false);

        assertEquals(expected, actual);
    }

    @Test
    void testReadUnsupportedExtensionFile() {
        assertThrows(IOException.class, () -> Parser.readFile(Paths.get(resourceDirectory, "testFile.txt").toString()));
    }
}
