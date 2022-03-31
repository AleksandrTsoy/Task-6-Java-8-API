import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        DAO dao = new DAO();
        LapTimeCalculate calculate = new LapTimeCalculate();
        Formatter formatter = new Formatter();
        StringBuilder result = formatter.formatter(calculate.calculate(dao.startLapDAO(), dao.endLapDAO()), dao.abbreviationsDAO());
        System.out.println(result);
    }
}
