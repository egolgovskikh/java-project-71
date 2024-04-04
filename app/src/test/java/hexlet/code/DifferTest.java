package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    @Test
    void testGenerateJson() throws IOException {
        String actual = Differ.generate(
                "src/test/resources/json/testFile1.json",
                "src/test/resources/json/testFile2.json",
                "stylish"
        );
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

    @Test
    void testGenerateJsonWithoutStyle() throws IOException {
        String actual = Differ.generate(
                "src/test/resources/json/testFile1.json",
                "src/test/resources/json/testFile2.json"
        );
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

    @Test
    void testGenerateYml() throws IOException {
        String actual = Differ.generate(
                "src/test/resources/yml/testFile1.yml",
                "src/test/resources/yml/testFile2.yml",
                "stylish"
        );
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
