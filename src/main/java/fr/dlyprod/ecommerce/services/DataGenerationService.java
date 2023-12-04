package fr.dlyprod.ecommerce.services;

import com.github.javafaker.Faker;
import fr.dlyprod.ecommerce.entities.*;
import fr.dlyprod.ecommerce.entities.enumes.RoleEnum;
import fr.dlyprod.ecommerce.repositories.AddressRepository;
import fr.dlyprod.ecommerce.repositories.ArticleRepository;
import fr.dlyprod.ecommerce.repositories.UserRepository;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class DataGenerationService {
    private AddressRepository addressRepository;
    private ArticleRepository articleRepository;
    private UserRepository userRepository;
    private final Faker faker;

    @Autowired
    public DataGenerationService(AddressRepository addressRepository, ArticleRepository articleRepository, UserRepository userRepository, Faker faker) {
        this.addressRepository = addressRepository;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.faker = faker;
    }

    /**
     * Génère des données ADDRESS
     * @param nombreDeDonnees
     */
    public void getFakerData(int nombreDeDonnees) {
        for (int i = 0; i < nombreDeDonnees; i++) {
            Address address = new Address();
            address.setNumero(Integer.parseInt(faker.address().buildingNumber()));
            address.setVille(faker.address().cityName());
            address.setCodePostal(faker.number().numberBetween(01000, 99000));

            addressRepository.save(address);
        }
    }

    /**
     * Genere des données ARTICLE
     * @param nombreDeDonnees
     */
    public void getDataArticle(int nombreDeDonnees) {
        for (int i = 0; i < nombreDeDonnees; i++) {
            Article article = new Article();
            article.setNom(faker.commerce().productName());
            article.setDescription(faker.lorem().characters(30));
            article.setPrix(faker.number().randomDouble(10,0,1000));
            article.setStock(faker.number().numberBetween(0,999));
            article.setVendable(isBool(i));
            article.setPhotos(faker.commerce().productName());

            articleRepository.save(article);
        }
    }

    /**
     * Genere des données USER
     * @param nombreDeDonnees
     */
    public void getDataUser(int nombreDeDonnees){
        for (int i = 0; i < nombreDeDonnees; i++) {
            Utilisateur user = new Utilisateur();
            user.setPrenom(faker.name().firstName());
            user.setNom(faker.name().lastName());
            user.setActif(isBool(i));//TODO renommé la method isBool
            user.setProfil(eRole());
            user.setEmail(faker.internet().emailAddress());
            user.setPassword(faker.internet().password());
            user.setTelephone(faker.phoneNumber().phoneNumber());
            user.setAdresses(findAddressById());

            userRepository.save(user);
        }

        /*private Set<Commande> commandes;
        @OneToMany(mappedBy = "utilisateur",cascade = CascadeType.ALL)
        private Set<CartePaiment> cartesDePaiment;
        @OneToMany(mappedBy = "utilisateur",cascade = CascadeType.ALL)
        private List<Comment> comments;
        @OneToMany(mappedBy = "utilisateur",cascade = CascadeType.ALL)
        private List<ArticlePanier> paniers;*/
    }

    /**
     * Renvoi un nombre aleator entre 0 et number
     * @param number
     * @return int
     */
    private int randomNumber(int number){
        Random random = new Random();
        int randomNumber = random.nextInt(number);
        return randomNumber;
    }
    public RoleEnum eRole(){
        RoleEnum role = null;
        Random random = new Random();
        int randomNumber = randomNumber(2);
        if(randomNumber == 0) role = RoleEnum.CLIENT;
        if(randomNumber == 1) role = RoleEnum.MAGASINIER;
        if(randomNumber == 2) role = RoleEnum.ADMIN;
        return role;
    }
    private boolean isBool(int i){
        return (i % 2) == 0 ? true : false;
    }

    private Set<Address> findAddressById(){
        int sizeAddress = addressRepository.findAll().size();
        Set<Address> addresses = new HashSet<>();

        for (int num = 0; num < 2; num++){
            Optional<Address> address = addressRepository.findById(Long.valueOf(randomNumber(sizeAddress)));
            addresses.add(address.get());
        }
        return addresses;
    }

}
