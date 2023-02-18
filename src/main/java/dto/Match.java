package dto;

import java.util.Date;
import java.util.Objects;

public class Match implements Comparable<Match> {

    private final Team awayTeam;
    private final Team localTeam;
    private int localTeamScore;
    private int awayTeamScore;
    private final Date creationDate;

    public Match(Team localTeam, Team awayTeam) {
        this.awayTeam = awayTeam;
        this.localTeam = localTeam;
        this.creationDate = new Date();
    }

    public void addLocalTeamScore(int scoreToAdd){
        localTeamScore+=scoreToAdd;
    }

    public void addAwayTeamScore(int scoreToAdd){
        awayTeamScore+=scoreToAdd;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public Team getLocalTeam() {
        return localTeam;
    }

    public int getLocalTeamScore() {
        return localTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Match)) return false;
        Match match = (Match) o;
        return localTeam.getName().equals(match.getLocalTeam().getName()) && this.awayTeam.getName().equals(match.getAwayTeam().getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(localTeamScore, awayTeamScore, creationDate);
    }

    @Override
    public String toString() {
        return localTeam.getName() + " - " + awayTeam.getName() + ": " + this.localTeamScore + " - " + this.awayTeamScore;
    }

    @Override
    public int compareTo(Match match) {
        if (this.getLocalTeamScore() + this.getAwayTeamScore() > match.getLocalTeamScore() + match.getAwayTeamScore()) {
            return -1;
        } else if (this.getLocalTeamScore() + this.getAwayTeamScore() < match.getLocalTeamScore() + match.getAwayTeamScore()) {
            return 1;
        } else {
            return this.getCreationDate().compareTo(match.getCreationDate());
        }
    }
}
