package fr.dlyprod.ecommerce.services;

import fr.dlyprod.ecommerce.entities.Utilisateur;
import fr.dlyprod.ecommerce.exceptions.user.EmailAlreadyExistsException;
import fr.dlyprod.ecommerce.exceptions.user.TelephoneAlreadyExistsException;
import fr.dlyprod.ecommerce.exceptions.user.UserNotFoundException;
import fr.dlyprod.ecommerce.forms.UserForm;
import fr.dlyprod.ecommerce.repositories.UserRepository;
import fr.dlyprod.ecommerce.services.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static fr.dlyprod.ecommerce.services.utils.UserUtils.convertToUser;
import static fr.dlyprod.ecommerce.services.utils.UserUtils.formatPhoneNumber;

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

    public Utilisateur createUser(UserForm userForm) {
        if (checkByEmail(userForm.getEmail()))
            throw new EmailAlreadyExistsException(userForm.getEmail());

        if (checkByTelephone(userForm.getTelephone()))
            throw new TelephoneAlreadyExistsException(formatPhoneNumber(userForm.getTelephone()));

        return userRepository.save(convertToUser(userForm));
    }


    public Utilisateur updateUser(Long id, UserForm userForm) throws RuntimeException{
        if (!userRepository.existsById(id))
            throw new UserNotFoundException(""+id);

        if (!checkEmailUser(id, userForm.getEmail())){
            if (checkByEmail(userForm.getEmail()))
                throw new EmailAlreadyExistsException(userForm.getEmail());
        }

        if (!checkNumberUser(id, userForm.getTelephone())){
            if (checkByTelephone(userForm.getTelephone()))
                throw new TelephoneAlreadyExistsException(formatPhoneNumber(userForm.getTelephone()));
        }

        Utilisateur user = getUtilisateurById(id);

        return userRepository.save(convertToUser(user, userForm));
    }

    public boolean deleteUtilisateur(Long id) {
        if (!userRepository.existsById(id)) return false;
        userRepository.deleteById(id);

        return true;
    }

    private boolean checkByEmail(String email) {
        return (userRepository.findEmailByEmail(email) != null) ? true : false;
    }

    private boolean checkEmailUser(Long id, String email){
        Utilisateur utilisateur =  findById(id);

        return (utilisateur.getEmail().equals(email)) ? true : false ;
    }

    private boolean checkNumberUser(Long id, String number){
        Utilisateur utilisateur =  findById(id);

        return (utilisateur.getTelephone().equals(number)) ? true : false ;
    }


    private Utilisateur findById(Long id){
        Utilisateur utilisateur = userRepository.findById(id).get();
        return utilisateur;
    }

    private boolean checkByTelephone(String telephone) {
        return (userRepository.findTelByTelephone(telephone) != null) ? true : false;
    }

    private boolean checkPassword(Long id, String password){
        Utilisateur utilisateur = userRepository.findById(id).get();
        String passwordEncoder = UserUtils.encryptPassword(password);

        return (utilisateur.getPassword().equals(passwordEncoder)) ? true : false;
    }


}
