package fr.dlyprod.ecommerce.services;

import fr.dlyprod.ecommerce.entities.*;
import fr.dlyprod.ecommerce.exceptions.article.PhotoFormatException;
import fr.dlyprod.ecommerce.exceptions.article.VideoFormatException;
import fr.dlyprod.ecommerce.exceptions.user.UserNotFoundException;
import fr.dlyprod.ecommerce.forms.ArticlerForm;
import fr.dlyprod.ecommerce.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static fr.dlyprod.ecommerce.services.utils.ArticleUtils.convertToArticle;
import static fr.dlyprod.ecommerce.services.utils.ArticleUtils.checkPhoto;
import static fr.dlyprod.ecommerce.services.utils.ArticleUtils.checkVideo;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> getAllArticle() {
        return articleRepository.findAll();
    }

    public Article getArticleById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article createArticle(ArticlerForm articlerForm) {
        //TODO ajt d'une method check photo/video
        return articleRepository.save(convertToArticle(articlerForm));
    }

    public Article updateArticle(Long id, ArticlerForm articlerForm) throws RuntimeException{
        if (!articleRepository.existsById(id))
            throw new UserNotFoundException("Erreur ID: "+ id +" non trouvé");
        if (checkPhoto(articlerForm.getPhotos()))
            throw new PhotoFormatException("Photo non conforme");
        if (checkVideo(articlerForm.getVideos()))
            throw new VideoFormatException("Video non conforme");

        Article article = getArticleById(id);

        return articleRepository.save(convertToArticle(article, articlerForm));
    }

    public Article findOne(Long id) {
        return articleRepository.findById(id).orElseThrow();
    }

    //TODO add in exception handler
    public void delete(Long id) {
        try{
            Article article = findOne(id);
            articleRepository.delete(article);
        }catch (Exception e){
            throw e;
        }

    }
}
