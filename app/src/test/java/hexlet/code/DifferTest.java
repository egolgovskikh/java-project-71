package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    @Test
    void testGenerate() throws IOException {
        String actual = Differ.generate(
                "src/test/resources/testFile1.json",
                "src/test/resources/testFile2.json",
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
    void testGenerateWithout () throws IOException {
        String actual = Differ.generate(
                "src/test/resources/testFile1.json",
                "src/test/resources/testFile2.json"
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
