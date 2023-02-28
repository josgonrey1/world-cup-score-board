package usecase;

import dao.MatchDAO;
import dto.Match;
import dto.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetMatchSummaryUseCase {

    private MatchDAO matchDAO;

    public List<Match> execute() {
        return matchDAO.getAll().stream().sorted().collect(Collectors.toList());
    }
}
