package fr.dlyprod.ecommerce.services.utils;

import fr.dlyprod.ecommerce.entities.Address;
import fr.dlyprod.ecommerce.forms.AddressForm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressUtils {
    public static Address converToAddress(AddressForm addressForm){
        Address address = new Address();
        address.setNumero(addressForm.getNumero());
        address.setVille(filtrerAddress(addressForm.getVille()));
        //TODO faut il ajt les utilisuateurs et les commandes dans le formulaire ??

        return address;
    }

    public static Address converToAddress(Address address, AddressForm addressForm){
        address.setNumero(addressForm.getNumero());
        address.setVille(filtrerAddress(addressForm.getVille()));
        //TODO faut il ajt les utilisuateurs et les commandes dans le formulaire ??

        return address;
    }

    private static String filtrerAddress(String address) {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\s]");
        Matcher matcher = pattern.matcher(address);

        return matcher.replaceAll("");
    }



}
