package fr.dlyprod.ecommerce.services;

import fr.dlyprod.ecommerce.entities.Address;
import fr.dlyprod.ecommerce.forms.AddressForm;
import fr.dlyprod.ecommerce.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static fr.dlyprod.ecommerce.services.utils.AddressUtils.converToAddress;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAllAddress(){
        return addressRepository.findAll();
    }

    public Address createAddress(AddressForm addressForm) {
        return addressRepository.save(converToAddress(addressForm));
    }
}
