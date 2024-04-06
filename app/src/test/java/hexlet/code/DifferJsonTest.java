package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferJsonTest {
    private final String resourceDirectory = Paths.get("src", "test", "resources").toString();
    private String jsonFile1;
    private String jsonFile2;

    @BeforeEach
    public void beforeEach() {
        jsonFile1 = Paths.get(resourceDirectory, "testFile1.json").toString();
        jsonFile2 = Paths.get(resourceDirectory, "testFile2.json").toString();
    }

    @Test
    void testGenerateJsonStylish() throws IOException {
        String actual = Differ.generate(
                jsonFile1,
                jsonFile2,
                "stylish"
        );
        String expected = new String(Files.readAllBytes(Paths.get(resourceDirectory,"expectedResultStylish.txt")));
        assertEquals(expected, actual);
    }

    @Test
    void testGenerateJsonPlain() throws IOException {
        String actual = Differ.generate(
                jsonFile1,
                jsonFile2,
                "plain"
        );
        String expected = new String(Files.readAllBytes(Paths.get(resourceDirectory,"expectedResultPlain.txt")));
        assertEquals(expected, actual);
    }

    @Test
    void testGenerateJsonWithoutFormat() throws IOException {
        String actual = Differ.generate(
                jsonFile1,
                jsonFile2
        );
        String expected = new String(Files.readAllBytes(Paths.get(resourceDirectory,"expectedResultStylish.txt")));
        assertEquals(expected, actual);
    }

    @Test
    void testGenerateJsonInJsonFormat() throws IOException {
        String actual = Differ.generate(
                jsonFile1,
                jsonFile2,
                "json"
        );
        String expected = new String(Files.readAllBytes(Paths.get(resourceDirectory,"expectedResultJson.json")));
        assertEquals(expected, actual);
    }
}
