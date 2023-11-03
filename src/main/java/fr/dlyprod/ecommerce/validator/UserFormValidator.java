package fr.dlyprod.ecommerce.validator;

import fr.dlyprod.ecommerce.entities.Utilisateur;
import fr.dlyprod.ecommerce.forms.UserForm;
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
        String regexString = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        UserForm userForm = (UserForm) target;

        //TODO ajouter une contrainte de size max
        // Validation pour le champ "nom"
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nom", "field.required");
        if (userForm.getNom() != null && userForm.getNom().length() < 3) {
            errors.rejectValue("nom", "field.minlength", new Object[]{3}, null);
        }

        //TODO ajouter une contrainte de size max
        // Validation pour le champ "prenom"
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "prenom", "field.required");
        if (userForm.getPrenom() != null && userForm.getPrenom().length() < 3) {
            errors.rejectValue("prenom", "field.minlength", new Object[]{3}, null);
        }

        //TODO creer method pour renforcer l'authenticité
        // Validation pour le champ "email"
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required");
        if (userForm.getEmail() != null && !userForm.getEmail().matches(regexString)) {
            errors.rejectValue("email", "field.invalid");
        }

        //TODO ajout de contrainte password
        // Validation pour le champ "password"
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required");
        if (userForm.getPassword() != null && userForm.getPassword().length() < 5) {
            errors.rejectValue("password", "field.minlength", new Object[]{8}, null);
        }

        //TODO ajouter contrainte numero uniquement
        // Validation pour le champ "telephone"
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telephone", "field.required");
        if (userForm.getTelephone() != null && !isValidPhoneNumber(userForm.getTelephone())) {
            errors.rejectValue("telephone", "field.invalid");
        }

        //TODO Validator Role
        // Validation pour le champ "profil"
        /*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "profil", "field.required");
        if (utilisateurForm.getProfil() != null && !isValidRole(utilisateurForm.getProfil()))
        }*/

    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        String regexStr = "^(?:(?:\\+|00)33|0)\\s*[1-9](?:[\\s.-]*\\d{2}){4}$";
        return phoneNumber != null && phoneNumber.matches(regexStr);
    }

    /*private boolean isValidRole(RoleEnum roleString){
        RoleEnum roleEnum;
        for (String role : roleEnum){
            if (roleString.equals(role)) return true;
        }
        return false;
    }*/
}
