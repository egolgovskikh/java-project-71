package hexlet.code;

public class StringUtils {
    public static void addInString(StringBuilder str, char sign, String key, Object value) {
        str.append("\n  ").append(sign).append(" ").append(key).append(": ").append(value);
    }
}
