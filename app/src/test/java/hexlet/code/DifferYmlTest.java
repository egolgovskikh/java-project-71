package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferYmlTest {
    private final String resourceDirectory = Paths.get("src", "test", "resources").toString();
    private String ymlFile1;
    private String ymlFile2;

    @BeforeEach
    public void beforeEach() {
        ymlFile1 = Paths.get(resourceDirectory, "testFile1.yml").toString();
        ymlFile2 = Paths.get(resourceDirectory, "testFile2.yml").toString();
    }

    @Test
    void testGenerateYmlStylish() throws IOException {
        String actual = Differ.generate(
                ymlFile1,
                ymlFile2,
                "stylish"
        );
        String expected = new String(Files.readAllBytes(Paths.get(resourceDirectory,"expectedResultStylish.txt")));
        assertEquals(expected, actual);
    }

    @Test
    void testGenerateYmlWithoutFormat() throws IOException {
        String actual = Differ.generate(
                ymlFile1,
                ymlFile2
        );
        String expected = new String(Files.readAllBytes(Paths.get(resourceDirectory,"expectedResultStylish.txt")));
        assertEquals(expected, actual);
    }

    @Test
    void testGenerateYmlJson() throws IOException {
        String actual = Differ.generate(
                ymlFile1,
                ymlFile2,
                "json"
        );
        String expected = new String(Files.readAllBytes(Paths.get(resourceDirectory,"expectedResultJson.json")));
        assertEquals(expected, actual);
    }

    @Test
    void testGenerateYmlPlain() throws IOException {
        String actual = Differ.generate(
                ymlFile1,
                ymlFile2,
                "plain"
        );
        String expected = new String(Files.readAllBytes(Paths.get(resourceDirectory,"expectedResultPlain.txt")));
        assertEquals(expected, actual);
    }
}
