package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    private final String resourceDirectory = Paths.get("src", "test", "resources").toString();
    private final App app = new App();

    @BeforeEach
    public void beforeEach() {
        app.filepath1 = Paths.get(resourceDirectory, "testFile1.json").toString();
        app.filepath2 = Paths.get(resourceDirectory, "testFile2.json").toString();
    }

    @Test
    public void testCall() throws IOException {
        String actual = app.call();
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/expectedResultStylish.txt")));
        assertEquals(expected, actual);
    }
}
