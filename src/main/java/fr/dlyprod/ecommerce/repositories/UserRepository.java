package fr.dlyprod.ecommerce.repositories;

import fr.dlyprod.ecommerce.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<Utilisateur, Long> {

    @Query(
            value = "SELECT u.email FROM public.user u WHERE u.email = :email",
            nativeQuery = true)
    String findEmailByEmail(@Param("email") String email);

    @Query(value = "SELECT u.telephone FROM public.user u WHERE u.telephone = :telephone", nativeQuery = true)
    String findTelByTelephone(@Param("telephone") String telephone);


}
