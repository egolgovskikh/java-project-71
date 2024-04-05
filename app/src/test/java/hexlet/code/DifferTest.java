package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    @Test
    void testGenerateJsonStylish() throws IOException {
        String actual = Differ.generate(
                "src/test/resources/json/testFile1.json",
                "src/test/resources/json/testFile2.json",
                "stylish"
        );
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/expectedResultStylish")));
        assertEquals(expected, actual);
    }

    @Test
    void testGenerateJsonPlain() throws IOException {
        String actual = Differ.generate(
                "src/test/resources/json/testFile1.json",
                "src/test/resources/json/testFile2.json",
                "plain"
        );
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/expectedResultPlain")));
        assertEquals(expected, actual);
    }

    @Test
    void testGenerateJsonWithoutStyle() throws IOException {
        String actual = Differ.generate(
                "src/test/resources/json/testFile1.json",
                "src/test/resources/json/testFile2.json"
        );
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/expectedResultStylish")));
        assertEquals(expected, actual);
    }

    @Test
    void testGenerateYml() throws IOException {
        String actual = Differ.generate(
                "src/test/resources/yml/testFile1.yml",
                "src/test/resources/yml/testFile2.yml",
                "stylish"
        );
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/expectedResultStylish")));
        assertEquals(expected, actual);
    }
}
