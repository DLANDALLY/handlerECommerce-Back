package fr.dlyprod.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class LigneDeCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantite;
    private int prixUnitaire;
    private int remiseArticle;
    @ManyToOne
    @JoinColumn(name = "commande_id", referencedColumnName = "id")
    private Commande commande;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "article_id", referencedColumnName = "id")
    private Article article;
}
