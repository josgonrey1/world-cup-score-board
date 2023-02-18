package dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Championship {

    private List<Match> matchList;

    public Championship() {
        this.matchList = new ArrayList<>();
    }

    public void startMatch(Team localTeam, Team awayTeam) {
        matchList.add(new Match(localTeam, awayTeam));
    }

    public void finishMatch(Team localTeam, Team awayTeam) {
        matchList = matchList.stream()
                .filter(match -> !(match.getLocalTeam().equals(localTeam) && match.getAwayTeam().equals(awayTeam)))
                .collect(Collectors.toList());
    }

    public void updateScore(int scoreLocalTeam, int scoreAwayTeam, Team homeTeam, Team awayTeam) {
        matchList.stream()
                .filter(match -> match.getLocalTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam))
                .forEach(ma -> {
                    ma.addLocalTeamScore(scoreLocalTeam);
                    ma.addAwayTeamScore(scoreAwayTeam);
                });
    }

    public List<Match> getMatchSummary() {
        return matchList.stream().sorted().collect(Collectors.toList());
    }

    public List<Match> getMatchList() {
        return matchList;
    }

    public Optional<Match> getMatch(Team homeTeam, Team awayTeam) {
        return matchList.stream().filter(ma -> ma.getLocalTeam().equals(homeTeam) && ma.getAwayTeam().equals(awayTeam)).findFirst();
    }
}
