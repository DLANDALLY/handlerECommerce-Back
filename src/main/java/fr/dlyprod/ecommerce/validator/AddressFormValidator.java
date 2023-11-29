package fr.dlyprod.ecommerce.validator;

import fr.dlyprod.ecommerce.entities.Address;
import fr.dlyprod.ecommerce.forms.AddressForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AddressFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Address.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AddressForm addressForm = (AddressForm) target;

        // Validation pour la propriété numero
        if (addressForm.getNumero() <= 0) {
            errors.rejectValue("numero", "positive.number", "Le numéro doit être un entier positif.");
        }

        // Validation pour la propriété ville
        if (addressForm.getVille() == null || addressForm.getVille().isEmpty()) {
            errors.rejectValue("ville", "required.field", "La ville est obligatoire.");
        }
        if (!contentLetter(addressForm.getVille())) {
            errors.rejectValue("ville", "content.address", "L'adresse n'est pas conforme");
        }

        // Validation pour la propriété code postal
        if (addressForm.getCodePostal() <= 0) {
            errors.rejectValue("codePostal", "positive.number", "Le code postal doit être un entier positif.");
        }
        if (addressForm.getCodePostal() > 100000) {
            errors.rejectValue("codePostal", "Max.number", "Le code postal dépasse la norme autorisé");
        }
    }

    private boolean contentLetter(String address) {
        return address.matches(".*[a-zA-Z].*");
    }


}
