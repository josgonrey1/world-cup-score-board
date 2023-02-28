package interfaces;

import java.util.List;
import java.util.Optional;

public interface Dao<T,B> {

    Optional<T> get(T object);

    List<T> getAll();

    void save(T t);

    void update(T t, B scoreLocalTeam, B scoreAwayTeam);

    void delete(T t);
}