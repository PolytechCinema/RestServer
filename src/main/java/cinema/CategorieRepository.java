package cinema;

import org.springframework.data.repository.CrudRepository;

import cinema.Categorie;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CategorieRepository extends CrudRepository<Categorie, Long> {
    boolean existsByCode(String code);

    Categorie findOneByCode(String code);
}
