package fr.dlyprod.ecommerce.services;

import com.github.javafaker.Faker;
import fr.dlyprod.ecommerce.entities.Address;
import fr.dlyprod.ecommerce.entities.Article;
import fr.dlyprod.ecommerce.exceptions.address.AddressNotFoundException;
import fr.dlyprod.ecommerce.exceptions.article.PhotoFormatException;
import fr.dlyprod.ecommerce.exceptions.article.VideoFormatException;
import fr.dlyprod.ecommerce.exceptions.user.UserNotFoundException;
import fr.dlyprod.ecommerce.forms.AddressForm;
import fr.dlyprod.ecommerce.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static fr.dlyprod.ecommerce.services.utils.AddressUtils.converToAddress;
import static fr.dlyprod.ecommerce.services.utils.ArticleUtils.*;

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

    public Address updateAddress(Long id, AddressForm addressForm) throws RuntimeException {
        //TODO creer une exception pour l'adresse UserNotFoundException nest pas correct
        if (!addressRepository.existsById(id))
            throw new UserNotFoundException(""+ id);

        Address address = getAddressById(id);
        if(address == null)
            throw new AddressNotFoundException("Adresse n'a pas ete trouvé");

        return addressRepository.save(converToAddress(address, addressForm));
    }

    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }
}
