import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LapTimeCalculate {

    public LinkedHashMap<String, List<String>> calculate(Map<String, String> start, Map<String, String> end) {
        Map<String, String> lapTime = new HashMap<>(end);
        start.forEach(
                (key, value) -> lapTime.merge(key, value, (oldVal, newVal) -> {
                    try {
                        return parseString(oldVal, newVal);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return "error";
                }));
        LinkedHashMap<String, List<String>> sortedLapTime = new LinkedHashMap<>();
        lapTime.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> sortedLapTime.put(entry.getKey(), Collections.singletonList(entry.getValue())));
        return sortedLapTime;
    }

    public String parseString(String oldVal, String newVal) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
        SimpleDateFormat newDate = new SimpleDateFormat("mm:ss.SSS");
        long result = dateFormat.parse(oldVal).getTime() - dateFormat.parse(newVal).getTime();
        return newDate.format(result);
    }
}
