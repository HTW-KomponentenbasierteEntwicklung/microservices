package de.htwberlin.account.core.domain.service.interfaces;

import de.htwberlin.account.core.domain.model.Account;
import de.htwberlin.account.port.account.user.exception.AccountNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IAccountRepository extends JpaRepository<Account, UUID> {
    List<Account> findByUsername(String username) throws AccountNotFoundException;
}