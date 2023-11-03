package fr.dlyprod.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class CartePaiment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 128,nullable = false)
    private String nomProprietaire;
    @Column(length = 128,nullable = false)
    private String prenomProprietaire;
    @Column(length = 128,nullable = false, unique = true)
    private int numero;
    @Column(length = 10,nullable = false)
    private String dateFinValidite;
    @Column(length = 128,nullable = false)
    private int cryptogramme;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilisateur_id", foreignKey = @ForeignKey(name = "fk_cb_utilisateur_id"))
    private Utilisateur utilisateur;
}
