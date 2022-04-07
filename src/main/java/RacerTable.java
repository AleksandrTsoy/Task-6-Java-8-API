import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class RacerTable {
    private FileReader fileReader;

    public RacerTable(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    public List<Racer> racerListCreate() throws IOException, URISyntaxException {
        Stream<String> start = fileReader.fileReader("start.log");
        Stream<String> end = fileReader.fileReader("end.log");
        Stream<String> abbreviations = fileReader.fileReader("abbreviations.txt");
        Map<String, String> startLap = start.collect(toMap((p) -> p.substring(0, 3), (p) -> p.substring(14)));
        Map<String, String> endLap = end.collect(toMap((p) -> p.substring(0, 3), (p) -> p.substring(14)));
        Map<String, List<String>> abbreviationsMap = abbreviations.collect(toMap(
                (p) -> p.substring(0, 3),
                (p) -> Arrays.stream(p.substring(4).split("_")).toList()));
        Map<String, List<String>> racerMap = new HashMap<>(abbreviationsMap);
        calculateLapTime(startLap, endLap).forEach(
                (key, value) -> racerMap.merge(key, Collections.singletonList((value)), (oldVal, newVal) -> List.of(oldVal.get(0), oldVal.get(1), newVal.get(0))));
        List<Racer> racerList = new ArrayList<>();
        racerMap.forEach((key, value) -> racerList.add(new Racer(key, value.get(0), value.get(1), value.get(2))));
        return racerList;
    }

    private Map<String, String> calculateLapTime(Map<String, String> start, Map<String, String> end) {
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
