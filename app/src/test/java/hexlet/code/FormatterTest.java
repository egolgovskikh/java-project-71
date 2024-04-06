package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import static hexlet.code.Formatter.format;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FormatterTest {

    @Test
    void testFormatUnsupportedFormat() {
        assertThrows(IOException.class, () -> format(Map.of(), Map.of(), Set.of(), "text"));
    }
}