import java.util.*;

import static java.util.stream.Collectors.toList;

public class RacerTableFormatter  {
    private StringBuilder finishTable = new StringBuilder();
    int count = 0;

    public StringBuilder racerTableFormatter(List<Racer> racerTable) {
        List<Integer> pilotName = new ArrayList<>();
        racerTable.forEach((p) -> pilotName.add(p.getRacerFullName().length()));
        int pilotNameLengthMax = pilotName.stream().max(Integer::compareTo).get() + 1;
        List<Integer> pilotTeam = new ArrayList<>();
        racerTable.forEach((p) -> pilotTeam.add(p.getTeam().length()));
        int pilotTeamLengthMax = pilotTeam.stream().max(Integer::compareTo).get() + 1;
        List<Integer> lapTime = new ArrayList<>();
        racerTable.forEach((p) -> lapTime.add(p.getLapTime().length()));
        int lapTimeLengthMax = lapTime.stream().max(Integer::compareTo).get();
        int stringLength = pilotNameLengthMax + pilotTeamLengthMax + lapTimeLengthMax + 12;
        List<Racer> sortedList = racerTable.stream()
                .sorted(Comparator.comparing(Racer::getLapTime))
                .collect(toList());
        sortedList.forEach((p) -> line(finishTable, count, stringLength).append(String.format("%-3s", ++count + "."))
                .append(String.format("%-6s|", "(" + p.getAbbreviation() + ")"))
                .append(String.format("%-" + pilotNameLengthMax + "s|", p.getRacerFullName()))
                .append(String.format("%-" + pilotTeamLengthMax + "s|", p.getTeam()))
                .append(String.format("%-" + lapTimeLengthMax + "s", p.getLapTime()) + "\n"));
        return finishTable;
    }

    private StringBuilder line(StringBuilder finishTable, int count, int stringLength) {
        if (count == 15) {
            finishTable.append("-".repeat(stringLength) + "\n");
        }
        return finishTable;
    }
}
