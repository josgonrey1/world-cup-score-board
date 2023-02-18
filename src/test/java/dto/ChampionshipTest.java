package dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class ChampionshipTest {

    private Championship championship;
    private static final Team TEAM_MEXICO = new Team("Mexico");
    private static final Team TEAM_CANADA = new Team("Canada");
    private static final Team TEAM_SPAIN = new Team("Spain");
    private static final Team TEAM_BRAZIL = new Team("Brazil");
    private static final Team TEAM_GERMANY = new Team("Germany");
    private static final Team TEAM_FRANCE = new Team("France");
    private static final Team TEAM_URUGUAY = new Team("Uruguay");
    private static final Team TEAM_ITALY = new Team("Italy");
    private static final Team TEAM_ARGENTINA = new Team("Argentina");
    private static final Team TEAM_AUSTRALIA = new Team("Australia");

    @BeforeEach
    void setUp() {
        championship = new Championship();
        championship.startMatch(TEAM_ARGENTINA, TEAM_AUSTRALIA);
        championship.startMatch(TEAM_GERMANY, TEAM_FRANCE);
        championship.startMatch(TEAM_URUGUAY, TEAM_ITALY);
        championship.startMatch(TEAM_SPAIN, TEAM_BRAZIL);
        championship.startMatch(TEAM_MEXICO, TEAM_CANADA);

        championship.updateScore(1, 1, TEAM_GERMANY, TEAM_FRANCE);
        championship.updateScore(1, 1, TEAM_GERMANY, TEAM_FRANCE);
        championship.updateScore(10, 2, TEAM_SPAIN, TEAM_BRAZIL);
        championship.updateScore(6, 6, TEAM_URUGUAY, TEAM_ITALY);
        championship.updateScore(3, 1, TEAM_ARGENTINA, TEAM_AUSTRALIA);
        championship.updateScore(0, 5, TEAM_MEXICO, TEAM_CANADA);
    }

    @Test
    void startMatchTest() {
        championship.startMatch(TEAM_MEXICO, TEAM_CANADA);

        assert(championship.getMatchList().size() > 0);
        assert(championship.getMatchList().stream().anyMatch(match -> match.getAwayTeam().equals(TEAM_CANADA) && match.getLocalTeam().equals(TEAM_MEXICO)));
    }

    @Test
    void updateScoreTest() {
        championship.updateScore(1, 1, TEAM_URUGUAY, TEAM_ITALY);

        assert(championship.getMatch(TEAM_URUGUAY, TEAM_ITALY).isPresent());
        assert(championship.getMatch(TEAM_URUGUAY, TEAM_ITALY).get().getLocalTeamScore() == 7);
        assert(championship.getMatch(TEAM_URUGUAY, TEAM_ITALY).get().getAwayTeamScore() == 7);
    }

    @Test
    void getMatchSummaryTest() {
        List<Match> matchList = championship.getMatchSummary();
        assert(matchList.size() == 5);
        assert(matchList.get(0).equals(new Match(TEAM_URUGUAY, TEAM_ITALY)));
        assert(matchList.get(1).equals(new Match(TEAM_SPAIN, TEAM_BRAZIL)));
        assert(matchList.get(2).equals(new Match(TEAM_MEXICO, TEAM_CANADA)));
        assert(matchList.get(3).equals(new Match(TEAM_ARGENTINA, TEAM_AUSTRALIA)));
        assert(matchList.get(4).equals(new Match(TEAM_GERMANY, TEAM_FRANCE)));
    }

    @Test
    void finishMatchTest() {
        championship.finishMatch(TEAM_MEXICO, TEAM_CANADA);

        assert(!championship.getMatch(TEAM_MEXICO, TEAM_CANADA).isPresent());
    }
}