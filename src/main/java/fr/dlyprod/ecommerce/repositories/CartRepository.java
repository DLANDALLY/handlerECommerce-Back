package fr.dlyprod.ecommerce.repositories;

import fr.dlyprod.ecommerce.entities.ArticlePanier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<ArticlePanier, Long> {
}
