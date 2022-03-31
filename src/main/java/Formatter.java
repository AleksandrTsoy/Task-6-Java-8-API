import java.util.*;

public class Formatter {
    StringBuilder result = new StringBuilder();
    int count = 0;
    public StringBuilder formatter(LinkedHashMap<String, List<String>> calculate, Map<String, List<String>> abbreviations) {
        Map<String, List<String>> format = new LinkedHashMap<>(calculate);
        abbreviations.forEach(
                (key, value) -> format.merge(key, (value), (oldVal, newVal) -> List.of(newVal.get(0), newVal.get(1), oldVal.get(0))));
        format.forEach((key, value) -> line(result, count).append(String.format("%-3s",++count + ".") + String.format("%-6s|", "(" + key + ")")
                + String.format("%-18s|", value.get(0)) + String.format("%-26s|", value.get(1)) + String.format("%-10s", value.get(2)) + "\n"));
        return result;
    }

    public StringBuilder line(StringBuilder result, int count) {
        if (count == 15) {
            result.append("-".repeat(65) + "\n");
        }
        return result;
    }

}
