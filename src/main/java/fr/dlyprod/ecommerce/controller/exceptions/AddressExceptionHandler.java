package fr.dlyprod.ecommerce.controller.exceptions;

import fr.dlyprod.ecommerce.exceptions.address.AddressNotFoundException;
import fr.dlyprod.ecommerce.exceptions.user.EmailAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class AddressExceptionHandler {

    /**
     * Model exception
     */
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        // Log l'exception ou effectuer d'autres traitements
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Email "+ex.getEmail()+" déjà existant ");
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<String> addressNotFoundHandlerException(AddressNotFoundException ex) {
        // Log l'exception ou effectuer d'autres traitements
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Address non troubé : "+ex.getMessage());
    }
}
