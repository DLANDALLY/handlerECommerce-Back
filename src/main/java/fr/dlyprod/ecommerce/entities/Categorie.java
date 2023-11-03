package fr.dlyprod.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 128, nullable = false)
    private String nom;
    private byte remise;
    private boolean isRemiseCumulable;
    private String photo;
    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL)
    private List<Article> articles;
}
