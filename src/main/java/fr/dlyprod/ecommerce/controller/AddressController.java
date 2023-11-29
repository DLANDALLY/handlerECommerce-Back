package fr.dlyprod.ecommerce.controller;

import fr.dlyprod.ecommerce.entities.Address;
import fr.dlyprod.ecommerce.entities.Article;
import fr.dlyprod.ecommerce.forms.AddressForm;
import fr.dlyprod.ecommerce.forms.ArticlerForm;
import fr.dlyprod.ecommerce.services.AddressService;
import fr.dlyprod.ecommerce.validator.AddressFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressFormValidator addressFormValidator;

    @GetMapping
    public ResponseEntity<List<Address>> getAddress(){
        List<Address> address = addressService.getAllAddress();
        return (address != null) ?
                new ResponseEntity<>(address, HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createAddress(@RequestBody AddressForm addressForm, BindingResult result) {
        addressFormValidator.validate(addressForm, result);
        if (result.hasErrors())
            //return new ResponseEntity<>("Une erreur est survenue verifiés le champs :"+result.getFieldError().getField(), HttpStatus.CONFLICT);
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.CONFLICT);

        addressService.createAddress(addressForm);
        return new ResponseEntity<>("Adresse créé avec succès.", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateArticle(
            @PathVariable Long id, @RequestBody AddressForm addressForm, BindingResult result) {
        addressFormValidator.validate(addressForm, result);
        if (result.hasErrors())
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);

        try {
            Address updatedAddress = addressService.updateAddress(id, addressForm);
            return new ResponseEntity<>("Adresse à bien été modifier.", HttpStatus.ACCEPTED);}
        //TODO Ajouter gestion de exception
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());}
    }
}
