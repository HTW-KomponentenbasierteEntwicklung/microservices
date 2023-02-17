package de.htwberlin.account.core.domain.service.impl;

import de.htwberlin.account.core.domain.model.Account;
import de.htwberlin.account.core.domain.service.interfaces.IAccountRepository;
import de.htwberlin.account.core.domain.service.interfaces.IAccountService;
import de.htwberlin.account.port.account.user.exception.AccountNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService implements IAccountService {
    private final IAccountRepository accountRepository;
    public AccountService(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createNewAccountForUsername(String username) {
        Account createdAccount = new Account(username);
        accountRepository.save(createdAccount);
        return createdAccount;
    }

    @Override
    public Account updateAccount(UUID id, Account account) throws AccountNotFoundException {
        Account existingProduct = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
        BeanUtils.copyProperties(account, existingProduct, "id");
        return accountRepository.save(existingProduct);
    }

    @Override
    public Account getAccountById(UUID id) throws AccountNotFoundException {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));

    }

    @Override
    public List<Account> getAccountsByUsername(String username) throws AccountNotFoundException {
            return accountRepository.findByUsername(username);
    }
}
