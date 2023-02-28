package dao;

import dto.Match;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static config.TestMother.*;

@ExtendWith(MockitoExtension.class)
class MatchDAOTest {

    private MatchDAO matchDAO = new MatchDAO();

    @Test
    void get() {
        matchDAO.save(new Match(TEAM_MEXICO, TEAM_MEXICO));
        assert matchDAO.get(new Match(TEAM_MEXICO, TEAM_MEXICO)).isPresent();
    }

    @Test
    void getAll() {
        matchDAO.save(new Match(TEAM_ARGENTINA, TEAM_ITALY));
        matchDAO.save(new Match(TEAM_CANADA, TEAM_ITALY));
        assert matchDAO.getAll().size() == 2;
    }

    @Test
    void save() {
        matchDAO.save(new Match(TEAM_CANADA, TEAM_ITALY));
        assert matchDAO.getAll().size() == 1;
    }

    @Test
    void update() {
        Match match = new Match(TEAM_CANADA, TEAM_ITALY);
        matchDAO.save(match);
        matchDAO.update(match, 1, 2);
        assert matchDAO.get(match).isPresent();
        assert matchDAO.get(match).get().getLocalTeamScore() == 1;
        assert matchDAO.get(match).get().getAwayTeamScore() == 2;
    }

    @Test
    void delete() {
        Match match = new Match(TEAM_ARGENTINA, TEAM_FRANCE);
        matchDAO.save(match);
        matchDAO.delete(match);
        assert !matchDAO.get(match).isPresent();
    }
}