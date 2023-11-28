package fr.dlyprod.ecommerce.repositories;

import fr.dlyprod.ecommerce.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
