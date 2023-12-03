package fr.dlyprod.ecommerce.validator;

import fr.dlyprod.ecommerce.forms.CategoryForm;
import fr.dlyprod.ecommerce.services.utils.CategoryUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CategoryFormValidator implements Validator {


    private String nom;
    private byte remise;
    private boolean isRemiseCumulable;
    private String photo;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        CategoryForm categoryForm = (CategoryForm) target;

        // Validation pour le champ "nom"
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nom", "nameCategory.empty","Le champ nom est requis");
        if (categoryForm.getNom() == null || categoryForm.getNom().length() < 3 || categoryForm.getNom().length() > 30) {
            errors.rejectValue("nom", "lastName.minlength", new Object[]{3}, "Le nom doit comporter 3 lettres minimum");
        }

        //Validation pour le champ Photo
        if (!CategoryUtils.validateUrl(categoryForm.getPhoto())){
            errors.rejectValue("photo", "photo.invalide","L'url n'est pas conforme");
        }
    }
}
