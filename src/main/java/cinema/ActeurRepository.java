package cinema;

import org.springframework.data.repository.CrudRepository;

import cinema.Acteur;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ActeurRepository extends CrudRepository<Acteur, Long> {

    Acteur findByNomAndPrenom(String nom, String prenom);

    boolean existsByNomAndPrenom(String nom, String prenom);
}