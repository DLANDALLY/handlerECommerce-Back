package fr.dlyprod.ecommerce.forms;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryForm {
    @Column(length = 128, nullable = false)
    @NotNull(message = "Champ requis")
    private String nom;
    private byte remise;
    private boolean isRemiseCumulable;
    private String photo;

    public CategoryForm() {}

    public CategoryForm(String nom) {
        this.nom = nom;
    }

    public CategoryForm(String nom, String photo) {
        this.nom = nom;
        this.photo = photo;
    }
}
