package fr.dlyprod.ecommerce.validator;

import fr.dlyprod.ecommerce.entities.Utilisateur;
import fr.dlyprod.ecommerce.forms.UserForm;
import fr.dlyprod.ecommerce.services.utils.UserUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class UserFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Utilisateur.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserForm userForm = (UserForm) target;

        // Validation pour le champ "nom"
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nom", "lastName.empty","Le champ nom est requis");
        if (userForm.getNom() == null || userForm.getNom().length() < 3) {
            errors.rejectValue("nom", "lastName.minlength", new Object[]{3}, "Le nom doit comporter 3 lettres minimum");
        }
        if (userForm.getNom().length() > 30) {
            errors.rejectValue("nom", "lastName.maxlength", new Object[]{3}, "Le nom doit être inférieur à 30 lettres");
        }

        // Validation pour le champ "prenom"
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "prenom", "firstName.empty","Le champ prenom est requis");
        if (userForm.getPrenom() == null || userForm.getPrenom().length() < 3) {
            errors.rejectValue("prenom", "prenom.minlength", new Object[]{3}, "Le prénom doit comporter 3 lettres minimum");
        }
        if (userForm.getPrenom().length() > 30) {
            errors.rejectValue("prenom", "prenom.maxlength", new Object[]{3}, "Le prenom doit être inférieur à 30 lettres");
        }

        // Validation pour le champ "email"
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required", "L'adresse mail est requis");
        if (userForm.getEmail() == null) {
            errors.rejectValue("email", "email.invalid", "l'adresse mail est requis");
        }
        if(!UserUtils.validerEmail(userForm.getEmail()) || userForm.getEmail() == null){
            errors.rejectValue("email", "email.invalid", "L'adresse mail n'est pas valide");
        }

        // Validation pour le champ "password"
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required", "Le mot de passe est requis");
        if (userForm.getPassword() == null || userForm.getPassword().length() < 5) {
            errors.rejectValue("password", "password.size", new Object[]{8}, "Le mot de passe est null");
        }
        if (!UserUtils.checkPasswordConstraints(userForm.getPassword())) {
            errors.rejectValue("password", "password.contraint", new Object[]{8}, "Le mot de passe ne respecte pas les contraintes");
        }

        // Validation pour le champ "telephone"
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telephone", "telephone.required", "Le champ telephone est requis");
        if (userForm.getTelephone() == null || !UserUtils.isValidPhoneNumber(userForm.getTelephone())) {
            errors.rejectValue("telephone", "telephone.invalid", "Le numero n'est pas au bon format");
        }

        //TODO Validator Role
        // Validation pour le champ "profil"
        /*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "profil", "field.required");
        if (utilisateurForm.getProfil() != null && !isValidRole(utilisateurForm.getProfil()))
        }*/

    }
}
