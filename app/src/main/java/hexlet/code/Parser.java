package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Parser {

    public static Map<String, Object> readFile(String filepath) throws IOException {
        File file = new File(filepath);
        return getObjectMapper(getFileFormat(filepath)).readValue(file, new TypeReference<>() {
        });
    }

    private static ObjectMapper getObjectMapper(String extension) throws IOException {
        return switch (extension) {
            case "json" -> new ObjectMapper();
            case "yml" -> new YAMLMapper();
            default -> throw new IOException("Unsupported file extension: " + extension);
        };
    }

    private static String getFileFormat(String filepath) {
        if (filepath.endsWith(".yml")) {
            return "yml";
        }
        if (filepath.endsWith(".json")) {
            return "json";
        }
        return "Unsupported file extension: " + filepath.substring(filepath.indexOf("."));
    }
}
