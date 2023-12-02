package fr.dlyprod.ecommerce.controller;

import fr.dlyprod.ecommerce.entities.Categorie;
import fr.dlyprod.ecommerce.entities.Utilisateur;
import fr.dlyprod.ecommerce.exceptions.user.EmailAlreadyExistsException;
import fr.dlyprod.ecommerce.exceptions.user.TelephoneAlreadyExistsException;
import fr.dlyprod.ecommerce.forms.CategoryForm;
import fr.dlyprod.ecommerce.forms.UserForm;
import fr.dlyprod.ecommerce.services.CategoryService;
import fr.dlyprod.ecommerce.validator.UserFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    private CategoryFormValidator categoryFormValidator;

    @GetMapping
    public ResponseEntity<List<Categorie>> getAllUtilisateurs(){
        List<Categorie> categories = categoryService.getAllCategory();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryForm categoryForm, BindingResult result) {
        userFormValidator.validate(userForm, result);
        if (result.hasErrors())
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.CONFLICT);
        try {
            userService.createUser(userForm);
        }catch (EmailAlreadyExistsException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }catch (TelephoneAlreadyExistsException e){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>("Utilisateur créé avec succès.", HttpStatus.CREATED);
    }
}
