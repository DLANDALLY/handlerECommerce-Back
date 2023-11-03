package fr.dlyprod.ecommerce.entities;

import fr.dlyprod.ecommerce.entities.enumes.RoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name="utilisateur")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30,nullable = false)
    private String nom;
    @Column(length = 30,nullable = false)
    private String prenom;
    @Column(name = "actif",length = 1,nullable = false)
    private boolean isActif;
    @Column(length = 1,nullable = false)
    private RoleEnum profil;
    @Column(length = 128, nullable = false, unique = true)
    private String email;
    @Column(length = 128, nullable = false)
    private String password;
    @Column(length = 50, nullable = false, unique = true)
    private String telephone;
    @OneToMany(mappedBy = "utilisateur")
    private Set<Adresse> adresses;
    @OneToMany(mappedBy = "utilisateur",cascade = CascadeType.ALL)
    private Set<Commande> commandes;
    @OneToMany(mappedBy = "utilisateur",cascade = CascadeType.ALL)
    private Set<CartePaiment> cartesDePaiment;
    @OneToMany(mappedBy = "utilisateur",cascade = CascadeType.ALL)
    private List<Comment> comments;
    @OneToMany(mappedBy = "utilisateur",cascade = CascadeType.ALL)
    private List<ArticlePanier> paniers;

    public void setPassword(String password) {
        this.password = password;
    }

    //TODO adapter la method
    /*public boolean verifierMotDePasse(String motDePasse) {
        // Vérifier si le mot de passe correspond au mot de passe crypté en base de données
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(motDePasse, this.motDePasse);
    }*/
}
