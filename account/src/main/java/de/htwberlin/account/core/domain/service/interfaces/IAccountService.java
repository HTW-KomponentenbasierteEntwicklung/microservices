package de.htwberlin.account.core.domain.service.interfaces;

import de.htwberlin.account.core.domain.model.Account;
import de.htwberlin.account.port.account.user.exception.AccountNotFoundException;

import java.util.List;
import java.util.UUID;

public interface IAccountService {
/**
 * The Interface defines methods that can be applied to a Product.
 */

    /**
     * Creates a new account and it's UUID.
     * @param username the new account
     * @return the new account
     */
    Account createNewAccountForUsername(String username);

    /**
     * Overwrites a account when the Id was found.
     * Does not create a new account.
     * @param id Account Id
     * @param account new Account Information
     * @return the updated Account
     * @throws AccountNotFoundException when the Product Id wasn't found
     */
    Account updateAccount(UUID id, Account account) throws AccountNotFoundException;

    /**
     * Finds a single account by it's Username.
     * @param id Id in UUID format
     * @return the account
     * @throws AccountNotFoundException when product not found (null)
     */
    Account getAccountById(UUID id) throws AccountNotFoundException;

    List<Account> getAccountsByUsername(String username) throws AccountNotFoundException;

}
