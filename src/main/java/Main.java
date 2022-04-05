import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        RacerTable racers = new RacerTable();
        RacerTableFormatter racerTable = new RacerTableFormatter();
        System.out.println(racerTable.racerTableFormatter(racers.racerListCreate()));
    }
}
