package fr.dlyprod.ecommerce.services;

import com.github.javafaker.Faker;
import fr.dlyprod.ecommerce.entities.*;
import fr.dlyprod.ecommerce.entities.enumes.RoleEnum;
import fr.dlyprod.ecommerce.repositories.*;
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
    private CartRepository cartRepository;
    private CategoryRepository categoryRepository;

    private final Faker faker;

    @Autowired
    public DataGenerationService(AddressRepository addressRepository, ArticleRepository articleRepository, UserRepository userRepository, CartRepository cartRepository, CategoryRepository categoryRepository, Faker faker) {
        this.addressRepository = addressRepository;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.categoryRepository = categoryRepository;
        this.faker = faker;
    }

    /**
     * Génère des données ADDRESS
     * @param numberOfData
     */
    public void getFakerData(int numberOfData) {
        for (int i = 0; i < numberOfData; i++) {
            Address address = new Address();
            address.setNumero(Integer.parseInt(faker.address().buildingNumber()));
            address.setVille(faker.address().cityName());
            address.setCodePostal(faker.number().numberBetween(01000, 99000));

            addressRepository.save(address);
        }
    }

    /**
     * Genere des données ARTICLE
     * @param numberOfData
     */
    public void getDataArticle(int numberOfData) {
        for (int i = 0; i < numberOfData; i++) {
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
     * @param numberOfData
     */
    public void getDataUser(int numberOfData){
        for (int i = 0; i < numberOfData; i++) {
            Utilisateur user = new Utilisateur();
            user.setPrenom(faker.name().firstName());
            user.setNom(faker.name().lastName());
            user.setActif(isBool(i));
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

    public void getDataCart(int numberOfData) {
        for (int i = 0; i < numberOfData; i++) {
            ArticlePanier cart = new ArticlePanier();
            cart.setArticle(findArticleById());
            cart.setQuantite(findStock());
            cart.setUtilisateur(findUserById());

            cartRepository.save(cart);
        }
    }

    public void getCategorie(int numberOfData){
        for (int i = 0; i < numberOfData; i++) {
            Categorie categorie = new Categorie();
            categorie.setNom(faker.commerce().productName());
            categorie.setRemise((byte) faker.number().numberBetween(10, 80));
            categorie.setRemiseCumulable(faker.bool().bool());
            categorie.setArticles(getArticlesCategorie());
            categorie.setPhoto(faker.avatar().image());

            categoryRepository.save(categorie);
        }
    }


    //TODO Creer une class DataGenerationUtils
    /**
     * Renvoi un nombre aleator entre 0 et number
     * @param number
     * @return int
     */
    public int randomNumber(int number){
        Random random = new Random();
        int randomNumber = random.nextInt(number);
        return (randomNumber < 1) ? 1 : randomNumber ;
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

    public Set<Address> findAddressById(){
        int sizeAddress = addressRepository.findAll().size();
        Set<Address> addresses = new HashSet<>();

        for (int num = 0; num < 2; num++){
            Optional<Address> address = addressRepository.findById(Long.valueOf(randomNumber(sizeAddress)));
            addresses.add(address.get());
        }
        return addresses;
    }

    public Article findArticleById(){
        int sizeArticle = articleRepository.findAll().size();
        Optional<Article> address = articleRepository.findById(Long.valueOf(randomNumber(sizeArticle)));

        return address.get();
    }

    public Utilisateur findUserById(){
        int sizeUser = userRepository.findAll().size();
        int num = randomNumber(sizeUser);
        Optional<Utilisateur> user = userRepository.findById(Long.valueOf(num));
        return user.get();

    }

    public List<Article> getArticlesCategorie(){
        List<Article> articles = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            articles.add(findArticleById());
        }
        return articles;
    }

    public int findStock(){
        return findArticleById().getStock();
    }



}
