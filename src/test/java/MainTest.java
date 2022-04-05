import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MainTest {
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
                .append("19.(KMH) |Kevin Magnussen   |HAAS FERRARI              |01:13.393");
        return finishTableRacer;
    }

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

    @Mock
    RacerTable racerTableMock;

    @Mock
    RacerTableFormatter racerTableFormatterMock;

    @Test
    void testNumberAndOrderOfMethodCalls() throws IOException, URISyntaxException {
        Mockito.when(racerTableMock.racerListCreate()).thenReturn(racerList());
        Mockito.when(racerTableFormatterMock.racerTableFormatter(racerList())).thenReturn(finishTableRacer());
        String expected = String.valueOf(finishTableRacer());
        String actual = String.valueOf(racerTableFormatterMock.racerTableFormatter(racerTableMock.racerListCreate()));
        assertEquals(expected, actual);
        InOrder inOrder = Mockito.inOrder(racerTableMock, racerTableFormatterMock);
        inOrder.verify(racerTableMock).racerListCreate();
        inOrder.verify(racerTableFormatterMock).racerTableFormatter(racerList());
        verify(racerTableMock).racerListCreate();
        verify(racerTableFormatterMock).racerTableFormatter(racerList());
    }
}