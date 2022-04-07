import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        FileReader fileReader = new FileReader();
        RacerTable racers = new RacerTable(fileReader);
        RacerTableFormatter racerTable = new RacerTableFormatter();
        System.out.println(racerTable.racerTableFormatter(racers.racerListCreate()));
    }
}
