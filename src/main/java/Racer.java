public class Racer {
    private final String abbreviations;
    private final String pilotName;
    private final String team;
    private String lapTime;

    public Racer(String abbreviations, String pilotName, String team, String lapTime) {
        this.abbreviations = abbreviations;
        this.pilotName = pilotName;
        this.team = team;
        this.lapTime = lapTime;
    }

    public String getAbbreviations() {
        return abbreviations;
    }

    public String getPilotName() {
        return pilotName;
    }

    public String getTeam() {
        return team;
    }

    public String getLapTime() {
        return lapTime;
    }

}
