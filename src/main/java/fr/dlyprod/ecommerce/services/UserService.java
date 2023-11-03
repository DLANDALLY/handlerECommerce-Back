package fr.dlyprod.ecommerce.services;

import fr.dlyprod.ecommerce.entities.Utilisateur;
import fr.dlyprod.ecommerce.exceptions.user.EmailAlreadyExistsException;
import fr.dlyprod.ecommerce.exceptions.user.TelephoneAlreadyExistsException;
import fr.dlyprod.ecommerce.exceptions.user.UserNotFoundException;
import fr.dlyprod.ecommerce.forms.UserForm;
import fr.dlyprod.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Utilisateur> getAllUtilisateur() {
        return userRepository.findAll();
    }

    public Utilisateur getUtilisateurById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Utilisateur createUtilisateur(UserForm userForm) {
        if (checkByEmail(userForm.getEmail()))
            throw new EmailAlreadyExistsException(userForm.getEmail());

        if (checkByTelephone(userForm.getTelephone()))
            throw new TelephoneAlreadyExistsException(formatPhoneNumber(userForm.getTelephone()));

        return userRepository.save(convertToUser(userForm));
    }


    public Utilisateur updateUtilisateur(Long id, UserForm userForm) throws RuntimeException{
        if (!userRepository.existsById(id))
            throw new UserNotFoundException("Erreur ID: "+ id +" non trouvé");

        if (checkByEmail(userForm.getEmail()))
            throw new EmailAlreadyExistsException("Email déjà existant");

        if (checkByTelephone(userForm.getTelephone()))
            throw new TelephoneAlreadyExistsException("Numéro non conforme ou déjà existant");

        Utilisateur user = getUtilisateurById(id);

        return userRepository.save(convertToUser(user, userForm));
    }

    private boolean checkByEmail(String email) {
         return (userRepository.findEmailByEmail(email) != null) ? true : false;
    }

    private boolean checkByTelephone(String telephone) {
        return (userRepository.findTelByTelephone(telephone) != null) ? true : false;
    }

    public boolean deleteUtilisateur(Long id) {
        if (!userRepository.existsById(id)) return false;
        userRepository.deleteById(id);

        return true;
    }

    private Utilisateur convertToUser(UserForm userForm){
        Utilisateur user = new Utilisateur();
        user.setNom(userForm.getNom());
        user.setPrenom(userForm.getPrenom());
        user.setActif(userForm.isActif());
        user.setProfil(userForm.getProfil());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setTelephone(userForm.getTelephone());

        return user;
    }

    private Utilisateur convertToUser(Utilisateur user, UserForm userForm){
        user.setNom(userForm.getNom());
        user.setPrenom(userForm.getPrenom());
        user.setActif(userForm.isActif());
        user.setProfil(userForm.getProfil());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setTelephone(userForm.getTelephone());

        return user;
    }

    //TODO creer une class UtilsUser pour le class metier en static ?? a voir
    public static String formatPhoneNumber(String phoneNumber) {
        String cleanPhoneNumber = phoneNumber.replaceAll("[^0-9]", "");
        StringBuilder formattedNumber = new StringBuilder();
        for (int i = 0; i < cleanPhoneNumber.length(); i += 2) {
            if (i > 0) formattedNumber.append(".");
            formattedNumber.append(
                    cleanPhoneNumber.substring(
                            i, Math.min(i + 2, cleanPhoneNumber.length())));
        }
        return formattedNumber.toString();
    }
}
