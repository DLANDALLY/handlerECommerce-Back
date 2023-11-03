package fr.dlyprod.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String texte;
    @Column(length = 1)
    private byte note;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "article_id", foreignKey = @ForeignKey(name = "fk_article_id"), referencedColumnName = "id")
    private Article article;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "utilisateur_id", foreignKey = @ForeignKey(name = "fk_commentaire_utilisateur_id"), referencedColumnName = "id")
    private Utilisateur utilisateur;
}
