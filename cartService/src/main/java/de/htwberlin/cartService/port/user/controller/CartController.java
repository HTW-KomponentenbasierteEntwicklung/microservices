package de.htwberlin.cartService.port.user.controller;

import de.htwberlin.cartService.core.domain.model.Item;
import de.htwberlin.cartService.core.domain.services.exception.ItemNotFoundException;
import de.htwberlin.cartService.core.domain.services.interfaces.ICartService;
import de.htwberlin.cartService.core.domain.model.Cart;
import de.htwberlin.cartService.port.producer.CartProducer;
import de.htwberlin.cartService.port.user.exception.NoSuchItemExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.json.JSONObject;

import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
public class CartController {
    @Autowired
    private ICartService cartService;
    @Autowired
    private CartProducer cartProducer;

    @GetMapping("/cart")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Cart getCart(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){
        String username = getusernameFromRequestHeader(authorizationHeader);
        return cartService.getCartForUsername(username);
    }
    @PutMapping("/changeAmount")
    @ResponseStatus(HttpStatus.OK)
    public Cart changeAmountForItem(@RequestBody Item toUpdateItem, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws NoSuchItemExistsException {
        String username = getusernameFromRequestHeader(authorizationHeader);
        int difference = 0;
        try {
            difference = cartService.getAmountDifferenceOfItem(toUpdateItem);
            cartService.changeAmountOfItemInCart(toUpdateItem);
        } catch (ItemNotFoundException e) {
            throw new NoSuchItemExistsException(e);
        }
        cartProducer.changeAmountOfProducts(toUpdateItem, difference);
        return cartService.getCartForUsername(username);
    }
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public Cart addItemToCart(@RequestBody Item item){
        cartProducer.changeAmountOfProducts(item, item.getAmount());

        return cartService.addItemToCart(item);
    }
    private String getusernameFromRequestHeader(String authorizationHeader) {
        String accessToken = authorizationHeader.split(" ", 2)[1];
        String payloadEncoded = accessToken.split("\\.")[1];

        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payloadDecoded = new String(decoder.decode(payloadEncoded));
        JSONObject payloadJSON = new JSONObject(payloadDecoded);
        String username = payloadJSON.getString("preferred_username");
        return username;
    }
}
