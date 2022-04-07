import static java.util.stream.Collectors.toMap;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class RacerTableTest {
    private RacerTable racerTable;
    private static final String oldVal = "12:04:03.332";
    private static final String newVal = "12:02:58.917";
    private static final String resultVal = "01:04.415";
    private List<Racer> racerList() {
        List<Racer> racerList = new ArrayList<>();
        racerList.add(new Racer("VBM", "Valtteri Bottas", "MERCEDES", "01:12.434"));
        racerList.add(new Racer("SVF", "Sebastian Vettel", "FERRARI", "01:04.415"));
        racerList.add(new Racer("CSR", "Carlos Sainz", "RENAULT", "01:12.950"));
        racerList.add(new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER", "01:12.013"));
        racerList.add(new Racer("KMH", "Kevin Magnussen", "HAAS FERRARI", "01:13.393"));
        racerList.add(new Racer("SPF", "Sergio Perez", "FORCE INDIA MERCEDES", "01:12.848"));
        racerList.add(new Racer("SVM", "Stoffel Vandoorne", "MCLAREN RENAULT", "01:12.463"));
        racerList.add(new Racer("CLS", "Charles Leclerc", "SAUBER FERRARI", "01:12.829"));
        racerList.add(new Racer("BHS", "Brendon Hartley", "SCUDERIA TORO ROSSO HONDA", "01:13.179"));
        racerList.add(new Racer("LHM", "Lewis Hamilton", "MERCEDES", "01:12.460"));
        racerList.add(new Racer("LSW", "Lance Stroll", "WILLIAMS MERCEDES", "01:13.323"));
        racerList.add(new Racer("RGH", "Romain Grosjean", "HAAS FERRARI", "01:12.930"));
        racerList.add(new Racer("FAM", "Fernando Alonso", "MCLAREN RENAULT", "01:12.657"));
        racerList.add(new Racer("SSW", "Sergey Sirotkin", "WILLIAMS MERCEDES", "01:12.706"));
        racerList.add(new Racer("NHR", "Nico Hulkenberg", "RENAULT", "01:13.065"));
        racerList.add(new Racer("MES", "Marcus Ericsson", "SAUBER FERRARI", "01:13.265"));
        racerList.add(new Racer("EOF", "Esteban Ocon", "FORCE INDIA MERCEDES", "01:13.028"));
        racerList.add(new Racer("PGS", "Pierre Gasly", "SCUDERIA TORO ROSSO HONDA", "01:12.941"));
        racerList.add(new Racer("KRF", "Kimi Raikkonen", "FERRARI", "01:12.639"));
        return racerList;
    }

    private Map<String, String> lapTimeMap() {
        Map<String, String> lapTime = new HashMap<>();
        lapTime.put("VBM","01:12.434");
        lapTime.put("SVF","01:04.415");
        lapTime.put("CSR","01:12.950");
        lapTime.put("DRR","01:12.013");
        lapTime.put("KMH","01:13.393");
        lapTime.put("SPF","01:12.848");
        lapTime.put("SVM","01:12.463");
        lapTime.put("CLS","01:12.829");
        lapTime.put("BHS","01:13.179");
        lapTime.put("LHM","01:12.460");
        lapTime.put("LSW","01:13.323");
        lapTime.put("RGH","01:12.930");
        lapTime.put("FAM","01:12.657");
        lapTime.put("SSW","01:12.706");
        lapTime.put("NHR","01:13.065");
        lapTime.put("MES","01:13.265");
        lapTime.put("EOF","01:13.028");
        lapTime.put("PGS","01:12.941");
        lapTime.put("KRF","01:12.639");
        return lapTime;
    }

    @BeforeEach
    public void init() {
        racerTable = new RacerTable(new FileReader());
    }

    @Test
    void testRacerListCreate() throws IOException, URISyntaxException {
        List<Racer> expected = racerList();
        List<Racer> actual = racerTable.racerListCreate();
        assertEquals(expected, actual);
    }

    @Test
    void testLapTimeCalculate() throws IOException, URISyntaxException {
        Stream<String> start = new FileReader().fileReader("start.log");
        Stream<String> end = new FileReader().fileReader("end.log");
        Map<String, String> startLap = start.collect(toMap((p) -> p.substring(0, 3), (p) -> p.substring(14)));
        Map<String, String> endLap = end.collect(toMap((p) -> p.substring(0, 3), (p) -> p.substring(14)));
        Map<String, String> expected = lapTimeMap();
        try {
            Method method = RacerTable.class.getDeclaredMethod("lapTimeCalculate", Map.class, Map.class);
            method.setAccessible(true);
            assertEquals(expected, method.invoke(racerTable, startLap, endLap));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void  testParseStringWhenCalculateLapTime() {
        String expected = resultVal;
        try {
            Method method = RacerTable.class.getDeclaredMethod("parseString", String.class, String.class);
            method.setAccessible(true);
            assertEquals(expected, method.invoke(racerTable, oldVal, newVal));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}