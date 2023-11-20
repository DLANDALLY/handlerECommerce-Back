package fr.dlyprod.ecommerce.controller;

import fr.dlyprod.ecommerce.entities.Utilisateur;
import fr.dlyprod.ecommerce.exceptions.user.EmailAlreadyExistsException;
import fr.dlyprod.ecommerce.exceptions.user.TelephoneAlreadyExistsException;
import fr.dlyprod.ecommerce.forms.UserForm;
import fr.dlyprod.ecommerce.services.UserService;
import fr.dlyprod.ecommerce.validator.UserFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/utilisateur")
public class UtilisateurController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserFormValidator userFormValidator;

    @GetMapping
    public ResponseEntity<List<Utilisateur>> getAllUtilisateurs(){
        List<Utilisateur> users = userService.getAllUtilisateur();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable Long id) {
        Utilisateur user = userService.getUtilisateurById(id);
        return (user != null) ?
                new ResponseEntity<>(user, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createUtilisateur(@RequestBody UserForm userForm, BindingResult result) {
        userFormValidator.validate(userForm, result);
        if (result.hasErrors())
            return new ResponseEntity<>("Une erreur est survenue verifiés le champs :"+result.getFieldError().getField(), HttpStatus.CONFLICT);

        Utilisateur createdUser = userService.createUtilisateur(userForm);
        return new ResponseEntity<>("Utilisateur créé avec succès.", HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUtilisateur(
            @PathVariable Long id, @RequestBody UserForm userForm, BindingResult result) {
        userFormValidator.validate(userForm, result);
        if (result.hasErrors())
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);

        try {
            Utilisateur updatedUser = userService.updateUtilisateur(id, userForm);
            return new ResponseEntity<>("Utilisateur modifié avec succès.", HttpStatus.ACCEPTED);}

        catch (EmailAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.reject());}
        catch (TelephoneAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body((e.reject()));}
        catch (RuntimeException r) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();}
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        return  (userService.deleteUtilisateur(id)) ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
