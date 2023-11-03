package fr.dlyprod.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Setter
@Getter
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 128)
    private String numero;
    private Date dateCreation;
    private Date dateLivraison;
    private double totalRemise;
    private double fraisExpedition;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adresse_facturation_id", referencedColumnName = "id")
    private Adresse adresseFacturation;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adresse_livraison_id", referencedColumnName = "id")
    private Adresse adresseLivraison;
    @ManyToOne
    @JoinColumn(name = "utilisateur_id", foreignKey = @ForeignKey(name = "fk_commande_utilisateur_id"),referencedColumnName = "id")
    private Utilisateur utilisateur;
    @OneToMany(mappedBy = "commande",cascade = CascadeType.ALL)
    private Set<LigneDeCommande> lignesCommande;
    //private CartePaiment cartePaiementDefaut;

}
