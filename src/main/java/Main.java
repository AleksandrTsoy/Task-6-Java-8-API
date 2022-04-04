import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        RacerTable racers = new RacerTable();
        RacerTableFormatter racerTable = new RacerTableFormatter();
        System.out.println(racerTable.racerTableFormatter(racers.racerListCreate()));
    }
}
