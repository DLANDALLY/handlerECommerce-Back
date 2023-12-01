package fr.dlyprod.ecommerce.services.utils;

import fr.dlyprod.ecommerce.entities.Utilisateur;
import fr.dlyprod.ecommerce.forms.UserForm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserUtils {

    public static Utilisateur convertToUser(UserForm userForm){
        Utilisateur user = new Utilisateur();
        user.setNom(filterCaracteres(userForm.getNom()));
        user.setPrenom(filterCaracteres(userForm.getPrenom()));
        user.setActif(userForm.isActif());
        user.setProfil(userForm.getProfil());
        user.setEmail(userForm.getEmail());
        user.setPassword(encryptPassword(userForm.getPassword()));
        user.setTelephone(userForm.getTelephone());

        return user;
    }

    public static Utilisateur convertToUser(Utilisateur user, UserForm userForm){
        user.setNom(filterCaracteres(userForm.getNom()));
        user.setPrenom(filterCaracteres(userForm.getPrenom()));
        user.setActif(userForm.isActif());
        user.setProfil(userForm.getProfil());
        user.setEmail(userForm.getEmail());
        user.setPassword(encryptPassword(userForm.getPassword()));
        user.setTelephone(userForm.getTelephone());

        return user;
    }

    /**
     * Filtre les chiffres et caracteres spéciaux
     * @param texte
     * @return
     */
    public static String filterCaracteres(String texte) {
        Pattern pattern = Pattern.compile("[^a-zA-Z]");
        Matcher matcher = pattern.matcher(texte);

        return matcher.replaceAll("");
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

    public static String encryptPassword(String rawPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(rawPassword);
    }

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public static boolean validerEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    /**
     * Contraint password min 8 caract, une lettre majuscule, une lettre minuscule,
     * un chiffre, un carct spécial parmi !@#$%^&*()-_+=
     * @param password
     * @return boolean
     */
    public static boolean checkPasswordConstraints(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()-_+=]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        String regexStr = "^(?:(?:\\+|00)33|0)\\s*[1-9](?:[\\s.-]*\\d{2}){4}$";
        return phoneNumber.matches(regexStr);
    }
}
