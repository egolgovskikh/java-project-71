package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Parser {

    public static Map<String, Object> readFile(String filepath) throws IOException {
        File file = new File(filepath);
        String extension = Files.probeContentType(Path.of(filepath));
        return getObjectMapper(extension).readValue(file, new TypeReference<>() {
        });
    }

    private static ObjectMapper getObjectMapper(String extension) throws IOException {
        if (extension == null) {
            throw new IOException("Unsupported file extension");
        }
        return switch (extension) {
            case "application/json" -> new ObjectMapper();
            case "application/x-yaml" -> new YAMLMapper();
            default -> throw new IOException("Unsupported file extension");
        };
    }
}
