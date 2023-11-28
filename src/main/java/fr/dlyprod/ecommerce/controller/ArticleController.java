package fr.dlyprod.ecommerce.controller;

import fr.dlyprod.ecommerce.entities.Article;
import fr.dlyprod.ecommerce.forms.ArticlerForm;
import fr.dlyprod.ecommerce.services.ArticleService;
import fr.dlyprod.ecommerce.validator.ArticleFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleFormValidator articleFormValidator;

    @GetMapping
    public ResponseEntity<List<Article>> getArticles(){
        List<Article> articles = articleService.getAllArticle();
        return (articles != null) ?
                new ResponseEntity<>(articles, HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        Article article = articleService.getArticleById(id);
        return (article != null) ?
                new ResponseEntity<>(article, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createArticle(@RequestBody ArticlerForm articlerForm, BindingResult result) {
        articleFormValidator.validate(articlerForm, result);
        if (result.hasErrors())
            return new ResponseEntity<>("Une erreur est survenue verifiés le champs :"+result.getFieldError().getField(), HttpStatus.CONFLICT);

        articleService.createArticle(articlerForm);
        return new ResponseEntity<>("Article créé avec succès.", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateArticle(
            @PathVariable Long id, @RequestBody ArticlerForm articlerForm, BindingResult result) {
        articleFormValidator.validate(articlerForm, result);
        if (result.hasErrors())
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);

        try {
            Article updatedArticle = articleService.updateArticle(id, articlerForm);
            return new ResponseEntity<>("Article modifié avec succès.", HttpStatus.ACCEPTED);}
        //TODO Ajouter gestion de exception
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());}
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> singleDelete(@PathVariable Long id) {
        try {
            articleService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
