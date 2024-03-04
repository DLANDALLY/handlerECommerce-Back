package fr.dlyprod.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity @Setter @Getter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 128)
    private  String nom;
    @Lob
    private String description;
    @Column(nullable = false)
    private double prix;
    private int remise;
    @Column(nullable = false)
    private int stock;
    @Column(nullable = false)
    private boolean isVendable;
    private String photos;
    private String videos;
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Comment> comments;
    @ManyToOne
    @JoinColumn(name = "categorie_id", foreignKey = @ForeignKey(name = "fk_categorie_aticle_id"),referencedColumnName = "id")
    private  Categorie categorie;
    @OneToOne(mappedBy = "article")
    private LigneDeCommande ligneDeCommande;

    @JsonIgnore
    @OneToOne(mappedBy = "article", fetch = FetchType.EAGER)
    private ArticlePanier articlePanier;
}
