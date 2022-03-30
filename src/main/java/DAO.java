import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DAO {
    public Map<String,String> startLapDAO() throws IOException {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC/Greenwich"));
        Stream<String> start = Files.lines(Paths.get("start.log"));
        Map<String, String> startLap = start.distinct().collect(Collectors.toMap((p) -> p.substring(0, 3), (p) -> p.substring(14))); //Преобразовать в map, где первый символ ключ, второй символ значение
        return startLap;
    }

    public Map<String,String> endLapDAO() throws IOException, ParseException {
        Stream<String> end = Files.lines(Paths.get("end.log"));
        Map<String, String> endLap = end.distinct().collect(Collectors.toMap((p) -> p.substring(0, 3), (p) -> p.substring(14)));
        return endLap;
    }

    public Map<String, List<String>> abbreviationsDAO() throws IOException, ParseException {
        Stream<String> abbreviations = Files.lines(Paths.get("abbreviations.txt"));
        Map<String, List<String>> abbreviationsMap = abbreviations.distinct().collect(Collectors.toMap(
                (p) -> p.substring(0, 3),
                (p) -> Arrays.stream(p.substring(4).split("_")).toList()));
        return abbreviationsMap;
    }

}
