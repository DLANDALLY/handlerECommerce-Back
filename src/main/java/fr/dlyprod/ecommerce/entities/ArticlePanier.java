package fr.dlyprod.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class ArticlePanier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "article_id", foreignKey = @ForeignKey(name = "fk_article_panier_id"),referencedColumnName = "id")
    private Article article;
    private int quantite;
    @ManyToOne
    @JoinColumn(name = "utilisateur_id", foreignKey = @ForeignKey(name = "fk_utilisateur_id"),referencedColumnName = "id")
    private Utilisateur utilisateur;
}
