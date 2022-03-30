import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        DAO dao = new DAO();
        LapTimeCalculate calculate = new LapTimeCalculate();
        calculate.calculate(dao.startLapDAO(), dao.endLapDAO());
        System.out.println(dao.abbreviationsDAO());
    }
}
