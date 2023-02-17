package de.htwberlin.account.port.account.user.controller;

import de.htwberlin.account.core.domain.model.Account;
import de.htwberlin.account.core.domain.service.interfaces.IAccountService;
import de.htwberlin.account.port.account.user.exception.AccountNotFoundException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.Base64;
import java.util.List;
import java.util.UUID;

@RestController
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @GetMapping("/account")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Account getAccountByAuthorizationHeader(@RequestHeader (HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String accessToken = authorizationHeader.split(" ", 2)[1];
        String payloadEncoded = accessToken.split("\\.")[1];

        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payloadDecoded = new String(decoder.decode(payloadEncoded));
        JSONObject payloadJSON = new JSONObject(payloadDecoded);
        String username = payloadJSON.getString("preferred_username");
        List<Account> accountList = accountService.getAccountsByUsername(username);
        if(accountList.isEmpty()){
            return accountService.createNewAccountForUsername(username);
        }else{
            return accountList.get(0);
        }
    }

    @PutMapping(path="/account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Account update (@PathVariable("id") UUID id, @RequestBody Account accountUpdate) throws AccountNotFoundException {
        return accountService.updateAccount(id, accountUpdate);
    }


}
