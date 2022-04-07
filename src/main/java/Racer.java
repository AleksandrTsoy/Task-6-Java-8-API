import java.util.Objects;

public class Racer {
    private final String abbreviation;
    private final String racerFullName;
    private final String team;
    private String lapTime;

    public Racer(String abbreviation, String racerFullName, String team, String lapTime) {
        this.abbreviation = abbreviation;
        this.racerFullName = racerFullName;
        this.team = team;
        this.lapTime = lapTime;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getRacerFullName() {
        return racerFullName;
    }

    public String getTeam() {
        return team;
    }

    public String getLapTime() {
        return lapTime;
    }

    @Override
    public String toString() {
        return "Racer{" +
                "abbreviations='" + abbreviation + '\'' +
                ", pilotName='" + racerFullName + '\'' +
                ", team='" + team + '\'' +
                ", lapTime='" + lapTime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Racer racer = (Racer) o;
        return abbreviation.equals(racer.abbreviation) && racerFullName.equals(racer.racerFullName) && team.equals(racer.team) && lapTime.equals(racer.lapTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(abbreviation, racerFullName, team, lapTime);
    }
}
