package fr.dlyprod.ecommerce.validator;

import fr.dlyprod.ecommerce.entities.Article;
import fr.dlyprod.ecommerce.forms.ArticlerForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ArticleFormValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return Article.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ArticlerForm articlerForm = (ArticlerForm) target;

        //TODO ajouter une contrainte de size max
        // Validation pour le champ "nom"
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nom", "field.required");
        if (articlerForm.getNom() != null && articlerForm.getNom().length() < 3) {
            errors.rejectValue("nom", "field.minlength", new Object[]{3}, null);
        }

        // Validation pour le champ "description"
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "field.required");
        if (articlerForm.getDescription() != null && articlerForm.getDescription().length() < 3) {
            errors.rejectValue("description", "field.minlength", new Object[]{3}, null);
        }
        //TODO reste à faire
//        private double prix;
//        private int remise;
//        private int stock;
//        private boolean isVendable;
//        private String photos;
//        private String videos;

    }
}
