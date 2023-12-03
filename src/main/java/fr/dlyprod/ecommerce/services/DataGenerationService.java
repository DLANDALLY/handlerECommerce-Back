package fr.dlyprod.ecommerce.services;

import com.github.javafaker.Faker;
import fr.dlyprod.ecommerce.entities.*;
import fr.dlyprod.ecommerce.entities.enumes.RoleEnum;
import fr.dlyprod.ecommerce.repositories.AddressRepository;
import fr.dlyprod.ecommerce.repositories.ArticleRepository;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class DataGenerationService {
    private AddressRepository addressRepository;
    private ArticleRepository articleRepository;
    private final Faker faker;

    @Autowired
    public DataGenerationService(AddressRepository addressRepository, Faker faker) {
        this.addressRepository = addressRepository;
        this.faker = faker;
    }

    public void getFakerData(int nombreDeDonnees) {
        for (int i = 0; i < nombreDeDonnees; i++) {
            Address address = new Address();
            address.setNumero(Integer.parseInt(faker.address().buildingNumber()));
            address.setVille(faker.address().cityName());
            address.setCodePostal(faker.number().numberBetween(01000, 99000));

            addressRepository.save(address);
        }
    }

    public void getDataArticle(int nombreDeDonnees) {
        for (int i = 0; i < nombreDeDonnees; i++) {
            Article article = new Article();
            article.setNom(faker.commerce().productName());
            article.setDescription(faker.chuckNorris().fact());
            article.setPrix(Double.parseDouble(faker.commerce().price()));
            article.setStock(faker.number().numberBetween(0,999));
            article.setVendable(isVendable(i));
            article.setPhotos(faker.commerce().productName());

            articleRepository.save(article);
        }
    }

    public void getDataUser(int nombreDeDonnees){
        for (int i = 0; i < nombreDeDonnees; i++) {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setPrenom(faker.name().firstName());
            utilisateur.setNom(faker.name().lastName());
            utilisateur.setActif(isVendable(i));//TODO renommé la method isBool
            utilisateur.setPrenom(faker.name().firstName());
            utilisateur.setPrenom(faker.name().firstName());
            utilisateur.setPrenom(faker.name().firstName());
            utilisateur.setPrenom(faker.name().firstName());
            articleRepository.save(article);
        }
        @Column(name = "actif",length = 1,nullable = false)
        private boolean isActif;
        @Column(length = 1,nullable = false)
        private RoleEnum profil;
        @Column(length = 128, nullable = false, unique = true)
        private String email;
        @Column(length = 128, nullable = false)
        private String password;
        @Column(length = 50, nullable = false, unique = true)
        private String telephone;
        @OneToMany(mappedBy = "utilisateur")
        private Set<Address> adresses;
        @OneToMany(mappedBy = "utilisateur",cascade = CascadeType.ALL)
        private Set<Commande> commandes;
        @OneToMany(mappedBy = "utilisateur",cascade = CascadeType.ALL)
        private Set<CartePaiment> cartesDePaiment;
        @OneToMany(mappedBy = "utilisateur",cascade = CascadeType.ALL)
        private List<Comment> comments;
        @OneToMany(mappedBy = "utilisateur",cascade = CascadeType.ALL)
        private List<ArticlePanier> paniers;
    }

    public boolean isVendable(int i){
        return (i % 2) == 0 ? true : false;
    }



}
