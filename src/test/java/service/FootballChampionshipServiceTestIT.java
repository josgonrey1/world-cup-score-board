package service;

import dao.MatchDAO;
import dto.Match;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usecase.FinishMatchUseCase;
import usecase.GetMatchSummaryUseCase;
import usecase.StartMatchUseCase;
import usecase.UpdateScoreUseCase;
import static config.TestMother.*;

import java.util.List;

class FootballChampionshipServiceTestIT {

    private FootballChampionshipService footballChampionshipService;
    private MatchDAO matchDAO;
    private StartMatchUseCase startMatchUseCase;
    private FinishMatchUseCase finishMatchUseCase;
    private UpdateScoreUseCase updateScoreUseCase;
    private GetMatchSummaryUseCase getMatchSummaryUseCase;

    @BeforeEach
    void setUp() {
        matchDAO = new MatchDAO();
        startMatchUseCase = new StartMatchUseCase(matchDAO);
        finishMatchUseCase = new FinishMatchUseCase(matchDAO);
        updateScoreUseCase = new UpdateScoreUseCase(matchDAO);
        getMatchSummaryUseCase = new GetMatchSummaryUseCase(matchDAO);
        footballChampionshipService = new FootballChampionshipService(startMatchUseCase, finishMatchUseCase, updateScoreUseCase, getMatchSummaryUseCase);

        footballChampionshipService.startMatch(TEAM_ARGENTINA, TEAM_AUSTRALIA);
        footballChampionshipService.startMatch(TEAM_GERMANY, TEAM_FRANCE);
        footballChampionshipService.startMatch(TEAM_URUGUAY, TEAM_ITALY);
        footballChampionshipService.startMatch(TEAM_SPAIN, TEAM_BRAZIL);
        footballChampionshipService.startMatch(TEAM_MEXICO, TEAM_CANADA);

        footballChampionshipService.updateScore(1, 1, TEAM_GERMANY, TEAM_FRANCE);
        footballChampionshipService.updateScore(1, 1, TEAM_GERMANY, TEAM_FRANCE);
        footballChampionshipService.updateScore(10, 2, TEAM_SPAIN, TEAM_BRAZIL);
        footballChampionshipService.updateScore(6, 6, TEAM_URUGUAY, TEAM_ITALY);
        footballChampionshipService.updateScore(3, 1, TEAM_ARGENTINA, TEAM_AUSTRALIA);
        footballChampionshipService.updateScore(0, 5, TEAM_MEXICO, TEAM_CANADA);
    }

    @Test
    void startMatchTest() {
        footballChampionshipService.startMatch(TEAM_MEXICO, TEAM_CANADA);

        assert(matchDAO.getAll().size() > 0);
        assert(matchDAO.getAll().stream().anyMatch(match -> match.getAwayTeam().equals(TEAM_CANADA) && match.getLocalTeam().equals(TEAM_MEXICO)));
    }

    @Test
    void updateScoreTest() {
        footballChampionshipService.updateScore(1, 1, TEAM_URUGUAY, TEAM_ITALY);

        assert(matchDAO.get(new Match(TEAM_URUGUAY, TEAM_ITALY)).isPresent());
        assert(matchDAO.get(new Match(TEAM_URUGUAY, TEAM_ITALY)).get().getLocalTeamScore() == 7);
        assert(matchDAO.get(new Match(TEAM_URUGUAY, TEAM_ITALY)).get().getAwayTeamScore() == 7);
    }

    @Test
    void getMatchSummaryTest() {
        List<Match> matchList = footballChampionshipService.getMatchSummary();
        assert(matchList.size() == 5);
        assert(matchList.get(0).equals(new Match(TEAM_URUGUAY, TEAM_ITALY)));
        assert(matchList.get(1).equals(new Match(TEAM_SPAIN, TEAM_BRAZIL)));
        assert(matchList.get(2).equals(new Match(TEAM_MEXICO, TEAM_CANADA)));
        assert(matchList.get(3).equals(new Match(TEAM_ARGENTINA, TEAM_AUSTRALIA)));
        assert(matchList.get(4).equals(new Match(TEAM_GERMANY, TEAM_FRANCE)));
    }

    @Test
    void finishMatchTest() {
        footballChampionshipService.finishMatch(TEAM_MEXICO, TEAM_CANADA);

        assert(!footballChampionshipService.getMatchSummary().contains(new Match(TEAM_MEXICO, TEAM_CANADA)));
    }
}