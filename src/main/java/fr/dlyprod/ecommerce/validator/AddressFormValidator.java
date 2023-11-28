package fr.dlyprod.ecommerce.validator;

import fr.dlyprod.ecommerce.entities.Address;
import fr.dlyprod.ecommerce.forms.AddressForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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

        // Validation pour la propriété code postal
        if (addressForm.getCodePostal() <= 0) {
            errors.rejectValue("codePostal", "positive.number", "Le code postal doit être un entier positif.");
        }
    }
}
