package interfaces;

import dto.Match;
import dto.Team;

import java.util.List;

public interface ChampionshipService {
    void startMatch(Team localTeam, Team awayTeam);

    void finishMatch(Team localTeam, Team awayTeam);

    void updateScore(int scoreLocalTeam, int scoreAwayTeam, Team localTeam, Team awayTeam);

    List<Match> getMatchSummary();
}
