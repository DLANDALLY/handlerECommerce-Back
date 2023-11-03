package fr.dlyprod.ecommerce.forms;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ArticlerForm {
    @NotNull(message = "Le champs est requis")
    @NotBlank(message = "Le champs ne doit pas être vide")
    private  String nom;
    @NotNull(message = "Le champs est requis")
    @NotBlank(message = "Le champs ne doit pas être vide")
    private String description;
    @NotNull(message = "Le champs est requis")
    @NotBlank(message = "Le champs ne doit pas être vide")
    private double prix;
    private int remise;
    @Column(nullable = false)
    private int stock;
    private boolean isVendable; //TODO Voir pour ajt une contraine ??
    private String photos; //TODO Ajouter une contraine d'extension /securité / size file
    private String videos; //TODO Ajouter une contraine d'extension /securité / size file

    public ArticlerForm() {}

    public ArticlerForm(String nom, String description, double prix, int remise, int stock, boolean isVendable, String photos, String videos) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.remise = remise;
        this.stock = stock;
        this.isVendable = isVendable;
        this.photos = photos;
        this.videos = videos;
    }
}
