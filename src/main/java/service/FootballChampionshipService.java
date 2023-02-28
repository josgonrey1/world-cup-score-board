package service;

import dto.Match;
import dto.Team;
import usecase.FinishMatchUseCase;
import usecase.GetMatchSummaryUseCase;
import usecase.StartMatchUseCase;
import usecase.UpdateScoreUseCase;

import java.util.List;

public class FootballChampionshipService {

    private final StartMatchUseCase startMatchUseCase;

    private final FinishMatchUseCase finishMatchUseCase;

    private final UpdateScoreUseCase updateScoreUseCase;

    private final GetMatchSummaryUseCase getMatchSummaryUseCase;

    public FootballChampionshipService(StartMatchUseCase startMatchUseCase, FinishMatchUseCase finishMatchUseCase, UpdateScoreUseCase updateScoreUseCase, GetMatchSummaryUseCase getMatchSummaryUseCase) {
        this.startMatchUseCase = startMatchUseCase;
        this.finishMatchUseCase = finishMatchUseCase;
        this.updateScoreUseCase = updateScoreUseCase;
        this.getMatchSummaryUseCase = getMatchSummaryUseCase;
    }

    public void startMatch(Team localTeam, Team awayTeam) {
        startMatchUseCase.execute(new StartMatchUseCase.Request(localTeam, awayTeam));
    }

    public void finishMatch(Team localTeam, Team awayTeam) {
        finishMatchUseCase.execute(new FinishMatchUseCase.Request(localTeam, awayTeam));
    }

    public void updateScore(int scoreLocalTeam, int scoreAwayTeam, Team localTeam, Team awayTeam) {
        updateScoreUseCase.execute(new UpdateScoreUseCase.Request(scoreLocalTeam, scoreAwayTeam, localTeam, awayTeam));
    }

    public List<Match> getMatchSummary() {
        return getMatchSummaryUseCase.execute();
    }
}
