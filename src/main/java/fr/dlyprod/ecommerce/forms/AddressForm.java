package fr.dlyprod.ecommerce.forms;

import fr.dlyprod.ecommerce.entities.Address;
import fr.dlyprod.ecommerce.entities.Commande;
import fr.dlyprod.ecommerce.entities.Utilisateur;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressForm {
    @NotNull(message = "Le champs est requis")
    @NotBlank(message = "Le champs ne doit pas être vide")
    private int numero;
    @NotNull(message = "Le champs est requis")
    @NotBlank(message = "Le champs ne doit pas être vide")
    private String ville;
    @NotNull(message = "Le champs est requis")
    @NotBlank(message = "Le champs ne doit pas être vide")
    private int codePostal;
    private Utilisateur utilisateur;
    private Commande commande;
}
