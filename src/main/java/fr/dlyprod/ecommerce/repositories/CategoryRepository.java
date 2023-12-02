package fr.dlyprod.ecommerce.repositories;

import fr.dlyprod.ecommerce.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categorie, Long> {
}
