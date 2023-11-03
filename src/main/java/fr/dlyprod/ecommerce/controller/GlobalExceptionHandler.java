package fr.dlyprod.ecommerce.controller;

import fr.dlyprod.ecommerce.exceptions.user.EmailAlreadyExistsException;
import fr.dlyprod.ecommerce.exceptions.user.TelephoneAlreadyExistsException;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleSQLException(ConstraintViolationException ex) {
        // Log l'exception ou effectuer d'autres traitements
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur SQL est survenue : " + ex.getMessage());
    }

    @ExceptionHandler(HibernateException.class)
    public ResponseEntity<String> handleHibernateException(HibernateException ex) {
        // Log l'exception ou effectuer d'autres traitements
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur HibernateException est survenue : " + ex.getMessage());
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        // Log l'exception ou effectuer d'autres traitements
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Email "+ex.getEmail()+" déjà existant ");
    }

    @ExceptionHandler(TelephoneAlreadyExistsException.class)
    public ResponseEntity<String> handleTelephoneAlreadyExistsException(TelephoneAlreadyExistsException ex) {
        // Log l'exception ou effectuer d'autres traitements
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Le numero de telephone : "+ex.getTelephone()+" déjà utilisé");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        // Log l'exception ou effectuer d'autres traitements
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur inattendue est survenue : " + ex.getCause());
    }
}
