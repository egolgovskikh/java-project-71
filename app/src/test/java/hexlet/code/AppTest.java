package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AppTest {

    @Test
    public void testCall() throws IOException {
        App app = new App();
        app.filepath1 = "src/test/resources/json/testFile1.json";
        app.filepath2 = "src/test/resources/json/testFile2.json";

        String actual = app.call();
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/expectedResult")));

        assertEquals(expected, actual);
    }

    @Test
    public void testCallWithNonExistentFile() {
        App app = new App();
        app.filepath1 = "src/test/resources/json/nonExistentFile.json";
        app.filepath2 = "src/test/resources/json/testFile2.json";

        assertThrows(IOException.class, app::call);
    }
}
