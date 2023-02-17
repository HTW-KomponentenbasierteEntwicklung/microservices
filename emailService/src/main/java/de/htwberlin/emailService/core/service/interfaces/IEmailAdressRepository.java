package de.htwberlin.emailService.core.service.interfaces;

import de.htwberlin.emailService.core.model.EmailAdress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface IEmailAdressRepository extends JpaRepository<EmailAdress, UUID> {
    List<EmailAdress> findByUsername(String username);

}
