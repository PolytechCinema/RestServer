package cinema;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Andzura on 23/01/2018.
 */
public interface PersonnageRepository extends CrudRepository<Personnage, Long> {
    boolean existsByActeurAndFilm(Acteur acteur, Film film);

    Personnage findOneByActeurAndFilm(Acteur acteur, Film film);
}