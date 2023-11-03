package fr.dlyprod.ecommerce.repositories;

import fr.dlyprod.ecommerce.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
