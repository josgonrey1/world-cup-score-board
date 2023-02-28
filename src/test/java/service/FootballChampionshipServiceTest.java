package service;

import dto.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import usecase.FinishMatchUseCase;
import usecase.GetMatchSummaryUseCase;
import usecase.StartMatchUseCase;
import usecase.UpdateScoreUseCase;

import static config.TestMother.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FootballChampionshipServiceTest {

    @Autowired
    private FootballChampionshipService footballChampionshipService;

    @Mock
    private StartMatchUseCase startMatchUseCase;

    @Mock
    private FinishMatchUseCase finishMatchUseCase;

    @Mock
    private UpdateScoreUseCase updateScoreUseCase;

    @Mock
    private GetMatchSummaryUseCase getMatchSummaryUseCase;

    @BeforeEach
    void setUp() {
        footballChampionshipService = new FootballChampionshipService(startMatchUseCase, finishMatchUseCase, updateScoreUseCase, getMatchSummaryUseCase);
    }

    @Test
    void startMatch() {
        footballChampionshipService.startMatch(TEAM_MEXICO, TEAM_CANADA);

        verify(startMatchUseCase).execute(new StartMatchUseCase.Request(TEAM_MEXICO, TEAM_CANADA));
    }

    @Test
    void finishMatch() {
        footballChampionshipService.finishMatch(TEAM_MEXICO, TEAM_CANADA);

        verify(finishMatchUseCase).execute(new FinishMatchUseCase.Request(TEAM_MEXICO, TEAM_CANADA));
    }

    @Test
    void updateScore() {
        footballChampionshipService.updateScore(1, 2, TEAM_MEXICO, TEAM_CANADA);

        verify(updateScoreUseCase).execute(new UpdateScoreUseCase.Request(1, 2, TEAM_MEXICO, TEAM_CANADA));
    }

    @Test
    void getMatchSummary() {
        footballChampionshipService.getMatchSummary();

        verify(getMatchSummaryUseCase).execute();

    }
}