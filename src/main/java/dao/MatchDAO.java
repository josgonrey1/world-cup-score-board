package dao;

import dto.Match;
import interfaces.Dao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchDAO implements Dao<Match, Integer> {

    private List<Match> matchList;

    public MatchDAO() {
        matchList = new ArrayList<>();
    }

    @Override
    public Optional<Match> get(Match matchRec) {
        return matchList.stream()
                .filter(match ->
                        match.getLocalTeam().equals(matchRec.getLocalTeam()) &&
                                match.getAwayTeam().equals(matchRec.getAwayTeam())).findFirst();
    }

    @Override
    public List<Match> getAll() {
        return matchList;
    }

    @Override
    public void save(Match match) {
        matchList.add(match);
    }

    @Override
    public void update(Match matchRec, Integer scoreLocalTeam, Integer scoreAwayTeam) {
        matchList.stream()
                .filter(match -> match.getLocalTeam().equals(matchRec.getLocalTeam()) && match.getAwayTeam().equals(matchRec.getAwayTeam()))
                .forEach(ma -> {
                    ma.addLocalTeamScore(scoreLocalTeam);
                    ma.addAwayTeamScore(scoreAwayTeam);
                });
    }

    @Override
    public void delete(Match match) {
        matchList.remove(match);
    }
}
