package fr.dlyprod.ecommerce.services.utils;

import fr.dlyprod.ecommerce.entities.Address;
import fr.dlyprod.ecommerce.forms.AddressForm;

public class AddressUtils {
    public static Address converToAddress(AddressForm addressForm){
        Address address = new Address();
        address.setNumero(addressForm.getNumero());
        address.setVille(addressForm.getVille());
        //TODO faut il ajt les utilisuateurs et les commandes dans le formulaire ??

        return address;
    }

    public static Address converToAddress(Address address, AddressForm addressForm){
        address.setNumero(addressForm.getNumero());
        address.setVille(addressForm.getVille());
        //TODO faut il ajt les utilisuateurs et les commandes dans le formulaire ??

        return address;
    }

}
