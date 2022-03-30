import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class LapTimeCalculate {

    public Map<String, String> calculate(Map<String, String> start, Map<String, String> end) {
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
        System.out.println(lapTime);
        return lapTime;
    }

    public String parseString(String oldVal, String newVal) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.S");
        SimpleDateFormat newDate = new SimpleDateFormat("mm:ss.S");
        long result = dateFormat.parse(oldVal).getTime() - dateFormat.parse(newVal).getTime();
        return newDate.format(result);
    }

}
