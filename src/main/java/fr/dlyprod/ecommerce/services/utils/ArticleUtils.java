package fr.dlyprod.ecommerce.services.utils;

import fr.dlyprod.ecommerce.entities.Article;
import fr.dlyprod.ecommerce.forms.ArticlerForm;

public class ArticleUtils {
    public static Article convertToArticle(ArticlerForm articlerForm){
        Article article = new Article();
        article.setNom(articlerForm.getNom());
        article.setDescription(articlerForm.getDescription());
        article.setPrix(articlerForm.getPrix());
        article.setRemise(articlerForm.getRemise());
        article.setStock(articlerForm.getStock());
        article.setVendable(articlerForm.isVendable());
        article.setPhotos(articlerForm.getPhotos());
        article.setVideos(articlerForm.getVideos());

        return article;
    }

    public static Article convertToArticle(Article article, ArticlerForm articlerForm){
        article.setNom(articlerForm.getNom());
        article.setDescription(articlerForm.getDescription());
        article.setPrix(articlerForm.getPrix());
        article.setRemise(articlerForm.getRemise());
        article.setStock(articlerForm.getStock());
        article.setVendable(articlerForm.isVendable());
        article.setPhotos(articlerForm.getPhotos());
        article.setVideos(articlerForm.getVideos());

        return article;
    }

    public static boolean checkVideo(String videos) {
        return false;
    }

    public static boolean checkPhoto(String photos) {
        return false;
    }
}
