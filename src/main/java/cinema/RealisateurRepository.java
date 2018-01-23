package cinema;


import org.springframework.data.repository.CrudRepository;

/**
 * Created by Andzura on 23/01/2018.
 */

public interface RealisateurRepository extends CrudRepository<Realisateur, Long> {

        Realisateur findByNomAndPrenom(String nom, String prenom);

        boolean existsByNomAndPrenom(String nom, String prenom);
}