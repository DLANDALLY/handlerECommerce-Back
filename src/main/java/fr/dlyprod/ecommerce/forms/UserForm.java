package fr.dlyprod.ecommerce.forms;

import fr.dlyprod.ecommerce.entities.enumes.RoleEnum;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserForm {
    @NotNull(message = "Le champs est requis")
    @NotBlank(message = "Le champs ne doit pas être vide")
    private String nom;
    @NotNull(message = "Le champs est requis")
    @NotBlank(message = "Le champs ne doit pas être vide")
    private String prenom;
    private boolean isActif;
    private RoleEnum profil;
    @NotNull(message = "Le champs est requis")
    @NotBlank(message = "Le champs ne doit pas être vide")
    @Size(min = 3, max = 29)
    private String email;
    @NotNull(message = "Le champs est requis")
    @NotBlank(message = "Le champs ne doit pas être vide")
    private String password;
    @NotNull(message = "Le champs est requis")
    @NotBlank(message = "Le champs ne doit pas être vide")
    private String telephone;

    public UserForm() {}

    public UserForm(String nom, String prenom, boolean isActif, RoleEnum profil, String email, String password, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.isActif = isActif;
        this.profil = profil;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
    }
}
