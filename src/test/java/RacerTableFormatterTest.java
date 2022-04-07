import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class RacerTableFormatterTest {
    private RacerTableFormatter racerTableFormatter;
    private RacerTable racerTable;
    private StringBuilder finishTableRacer() {
        StringBuilder finishTableRacer = new StringBuilder();
        finishTableRacer.append("1. (SVF) |Sebastian Vettel  |FERRARI                   |01:04.415" + "\n")
                .append("2. (DRR) |Daniel Ricciardo  |RED BULL RACING TAG HEUER |01:12.013" + "\n")
                .append("3. (VBM) |Valtteri Bottas   |MERCEDES                  |01:12.434" + "\n")
                .append("4. (LHM) |Lewis Hamilton    |MERCEDES                  |01:12.460" + "\n")
                .append("5. (SVM) |Stoffel Vandoorne |MCLAREN RENAULT           |01:12.463" + "\n")
                .append("6. (KRF) |Kimi Raikkonen    |FERRARI                   |01:12.639" + "\n")
                .append("7. (FAM) |Fernando Alonso   |MCLAREN RENAULT           |01:12.657" + "\n")
                .append("8. (SSW) |Sergey Sirotkin   |WILLIAMS MERCEDES         |01:12.706" + "\n")
                .append("9. (CLS) |Charles Leclerc   |SAUBER FERRARI            |01:12.829" + "\n")
                .append("10.(SPF) |Sergio Perez      |FORCE INDIA MERCEDES      |01:12.848" + "\n")
                .append("11.(RGH) |Romain Grosjean   |HAAS FERRARI              |01:12.930" + "\n")
                .append("12.(PGS) |Pierre Gasly      |SCUDERIA TORO ROSSO HONDA |01:12.941" + "\n")
                .append("13.(CSR) |Carlos Sainz      |RENAULT                   |01:12.950" + "\n")
                .append("14.(EOF) |Esteban Ocon      |FORCE INDIA MERCEDES      |01:13.028" + "\n")
                .append("15.(NHR) |Nico Hulkenberg   |RENAULT                   |01:13.065" + "\n")
                .append("-----------------------------------------------------------------" + "\n")
                .append("16.(BHS) |Brendon Hartley   |SCUDERIA TORO ROSSO HONDA |01:13.179" + "\n")
                .append("17.(MES) |Marcus Ericsson   |SAUBER FERRARI            |01:13.265" + "\n")
                .append("18.(LSW) |Lance Stroll      |WILLIAMS MERCEDES         |01:13.323" + "\n")
                .append("19.(KMH) |Kevin Magnussen   |HAAS FERRARI              |01:13.393" + "\n");
        return finishTableRacer;
    }

    @BeforeEach
    public void init() {
        racerTableFormatter = new RacerTableFormatter();
        racerTable = new RacerTable(new FileReader());
    }

    @Test
    void testRacerTableFormatter() throws IOException, URISyntaxException {
        String actual = String.valueOf(racerTableFormatter.racerTableFormatter(racerTable.racerListCreate()));
        String expected = String.valueOf(finishTableRacer());
        assertEquals(expected, actual);
    }
}