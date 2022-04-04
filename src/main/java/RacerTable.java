import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class RacerTable {

    public List<Racer> racerListCreate() throws IOException {
        Stream<String> start = new DAO("start.log").fileReader();
        Stream<String> end = new DAO("end.log").fileReader();
        Stream<String> abbreviations = new DAO("abbreviations.txt").fileReader();
        Map<String, String> startLap = start.distinct().collect(toMap((p) -> p.substring(0, 3), (p) -> p.substring(14)));
        Map<String, String> endLap = end.distinct().collect(toMap((p) -> p.substring(0, 3), (p) -> p.substring(14)));
        Map<String, List<String>> abbreviationsMap = abbreviations.distinct().collect(toMap(
                (p) -> p.substring(0, 3),
                (p) -> Arrays.stream(p.substring(4).split("_")).toList()));
        Map<String, List<String>> racerMap = new HashMap<>(abbreviationsMap);
        lapTimeCalculate(startLap, endLap).forEach(
                (key, value) -> racerMap.merge(key, Collections.singletonList((value)), (oldVal, newVal) -> List.of(oldVal.get(0), oldVal.get(1), newVal.get(0))));
        List<Racer> racerList = new ArrayList<>();
        racerMap.forEach((key, value) -> racerList.add(new Racer(key, value.get(0), value.get(1), value.get(2))));
        return racerList;
    }

    private Map<String, String> lapTimeCalculate(Map<String, String> start, Map<String, String> end) {
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
        return lapTime;
    }

    private String parseString(String oldVal, String newVal) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
        SimpleDateFormat newDate = new SimpleDateFormat("mm:ss.SSS");
        long result = dateFormat.parse(oldVal).getTime() - dateFormat.parse(newVal).getTime();
        return newDate.format(result);
    }
}
