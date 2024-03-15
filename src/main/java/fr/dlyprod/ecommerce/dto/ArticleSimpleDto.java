package fr.dlyprod.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.dlyprod.ecommerce.entities.ArticlePanier;
import fr.dlyprod.ecommerce.entities.Categorie;
import fr.dlyprod.ecommerce.entities.Comment;
import fr.dlyprod.ecommerce.entities.LigneDeCommande;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArticleSimpleDto {
    private Long id;
    private  String nom;
    private String description;
    private double prix;
    private int remise;
    private int stock;
    private boolean isVendable;
    private String photos;
    private String videos;
}
