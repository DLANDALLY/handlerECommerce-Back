package fr.dlyprod.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "adresse")
@Setter
@Getter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20)
    private int numero;
    @Column(length = 128)
    private String ville;
    @Column(length = 20)
    private int codePostal;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilisateur_id")
    private User user;*/

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Utilisateur.class)
    @JoinColumn(name = "utilisateur_id",
            foreignKey = @ForeignKey(name = "fk_adresse_utilisateur_id"),
            referencedColumnName = "id")
    private Utilisateur utilisateur;

    @OneToOne(mappedBy = "address")
    private Commande commande;
}
