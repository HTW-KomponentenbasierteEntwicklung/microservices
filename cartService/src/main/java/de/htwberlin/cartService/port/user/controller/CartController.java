package de.htwberlin.cartService.port.user.controller;

import de.htwberlin.cartService.core.domain.model.Item;
import de.htwberlin.cartService.core.domain.services.exception.ItemNotFoundException;
import de.htwberlin.cartService.core.domain.services.interfaces.ICartService;
import de.htwberlin.cartService.core.domain.model.Cart;
import de.htwberlin.cartService.port.producer.CartProducer;
import de.htwberlin.cartService.port.user.exception.AmountCannotBeLessThanZeroException;
import de.htwberlin.cartService.port.user.exception.NoSuchItemExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.json.JSONObject;

import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.UUID;

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

    @PostMapping("/cart")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Cart addItemToCart(@RequestBody Item item, @RequestParam String username) {
        cartProducer.changeAmountOfProducts(item, -1);
        return cartService.addItemToCart(item, username);
    }

    @DeleteMapping("/cart/{idItem}")
    @ResponseStatus(HttpStatus.OK)
    public Cart deleteItemFromCart(@PathVariable("idItem") UUID idItem) throws ItemNotFoundException {
        Item item = cartService.getItemById(idItem);
        cartProducer.changeAmountOfProducts(item, 1);
        Cart returnCart = cartService.deleteItemFromCart(idItem);
        return returnCart;
    }

}
