package cinema;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Andzura on 23/01/2018.
 */
public interface FilmRepository extends CrudRepository<Film, Long> {
    boolean existsByTitre(String titre);
}