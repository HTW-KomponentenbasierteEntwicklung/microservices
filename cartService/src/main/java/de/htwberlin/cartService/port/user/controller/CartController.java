package de.htwberlin.cartService.port.user.controller;

import de.htwberlin.cartService.core.domain.model.Item;
import de.htwberlin.cartService.core.domain.services.exception.ItemNotFoundException;
import de.htwberlin.cartService.core.domain.services.interfaces.ICartService;
import de.htwberlin.cartService.core.domain.model.Cart;
import de.htwberlin.cartService.port.producer.CartProducer;
import de.htwberlin.cartService.port.user.exception.AmountCannotBeLessThanZeroException;
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
    public @ResponseBody Cart getCart(@RequestParam String username){
        return cartService.getCartForUsername(username);
    }
    @PutMapping("/changeAmount")
    @ResponseStatus(HttpStatus.OK)
    public Cart changeAmountForItem(@RequestBody Item toUpdateItem, @RequestParam String username) throws NoSuchItemExistsException {
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
    @PostMapping("/cart")
    @ResponseStatus(HttpStatus.OK)
    public Cart addItemToCart(@RequestBody Item item, @RequestParam String username) throws AmountCannotBeLessThanZeroException {
        if(item.getAmount() <= 0){
            throw new AmountCannotBeLessThanZeroException();
        }
        cartProducer.changeAmountOfProducts(item, item.getAmount());
        return cartService.addItemToCart(item, username);
    }

}
