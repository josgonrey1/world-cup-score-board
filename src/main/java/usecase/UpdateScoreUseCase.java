package usecase;

import dao.MatchDAO;
import dto.Match;
import dto.Team;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateScoreUseCase {

    private MatchDAO matchDAO;

    public void execute(Request req) {
        matchDAO.update(new Match(req.getLocalTeam(), req.getAwayTeam()), req.getScoreLocalTeam(), req.getScoreAwayTeam());
    }

    @Getter
    @AllArgsConstructor
    @EqualsAndHashCode
    public static final class Request {
        private int scoreLocalTeam;
        private int scoreAwayTeam;
        private Team localTeam;
        private Team awayTeam;
    }
}
