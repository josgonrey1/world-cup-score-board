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
public class FinishMatchUseCase {

    private MatchDAO matchDAO;

    public void execute(Request req) {
        matchDAO.delete(new Match(req.getLocalTeam(), req.getAwayTeam()));
    }

    @Getter
    @AllArgsConstructor
    @EqualsAndHashCode
    public static final class Request {
        private Team localTeam;
        private Team awayTeam;
    }
}
