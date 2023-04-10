package de.htwberlin.cartService.unitTests.core.domain.services.impl;

import de.htwberlin.cartService.core.domain.model.Cart;
import de.htwberlin.cartService.core.domain.model.Item;
import de.htwberlin.cartService.core.domain.services.exception.ItemNotFoundException;
import de.htwberlin.cartService.core.domain.services.impl.CartService;
import de.htwberlin.cartService.core.domain.services.interfaces.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    CartService cartService;

    @Test
    public void changeAmountOfItemInCartValidWithFullCartTest() throws ItemNotFoundException {
        String username = "testUser";
        Item newItem = new Item();
        newItem.setId(UUID.randomUUID());
        newItem.setProductname("product1");
        newItem.setAmount(1);
        newItem.setUsername(username);
        newItem.setProductPrice(BigDecimal.valueOf(10.0));

        when(itemRepository.save(newItem)).thenReturn(newItem);
        Item savedItem = itemRepository.save(newItem);
        System.out.println(savedItem.toString());

//Todo
        when(itemRepository.findById(newItem.getId())).thenReturn(Optional.of(newItem));
        System.out.println(itemRepository.findByUsername(username));

        System.out.println(itemRepository.findByUsername(username));

        newItem.setAmount(3);
        Cart cart = cartService.changeAmountOfItemInCart(newItem, username);

        assertEquals(3, cart.getTotalAmount());
        verify(itemRepository).save(newItem);
    }

/*
    @Test
    public void changeAmountOfItemInCartValidWithFullCartTest() throws ItemNotFoundException {
        String username = "testUser";
        Item item = new Item();
        UUID id = UUID.randomUUID();
        item.setId(id);
        item.setProductname("product1");
        item.setAmount(1);
        item.setUsername(username);
        item.setProductPrice(BigDecimal.valueOf(10.0));

        Item toUpdateItem = new Item();
        toUpdateItem.setId(id);
        toUpdateItem.setProductname("product1");
        toUpdateItem.setAmount(3);
        toUpdateItem.setUsername(username);
        toUpdateItem.setProductPrice(BigDecimal.valueOf(10.0));

        Mockito.when(itemRepository.findById(toUpdateItem.getId())).thenReturn(Optional.of(toUpdateItem));

        cartService.changeAmountOfItemInCart(item, username);

        Mockito.verify(itemRepository).save(toUpdateItem);
    }*/


    @Test
    public void changeAmountOfItemInCartNotExistingProductTest() {
        String username = "testUser";
        Item item = new Item();
        item.setProductname("product1");
        item.setAmount(1);
        item.setUsername(username);
        item.setProductPrice(BigDecimal.valueOf(10.0));
        when(itemRepository.findById(item.getId())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> cartService.changeAmountOfItemInCart(item, username));
    }

    @Test
    public void changeAmountOfItemInCartNullItem() {
        String username = "testUser";
        assertThrows(IllegalArgumentException.class, () -> cartService.changeAmountOfItemInCart(null, username));
    }

    @Test
    public void getCartForUsernameTest() {
        String username = "testUser";
        Item item1 = new Item();
        item1.setProductname("product1");
        item1.setAmount(1);
        item1.setUsername(username);
        item1.setProductPrice(BigDecimal.valueOf(10.0));
        Item item2 = new Item();
        item2.setProductname("product2");
        item2.setAmount(2);
        item2.setUsername(username);
        item2.setProductPrice(BigDecimal.valueOf(20.0));
        when(itemRepository.findByUsername(username)).thenReturn(Arrays.asList(item1, item2));

        Cart cart = cartService.getCartForUsername(username);

        assertEquals(2, cart.getItems().size());    //TODO
        assertEquals(BigDecimal.valueOf(50.0), cart.getTotalAmount());
    }

    @Test
    void addItemToCartNewTest() {
        String username = "testUser";
        Item newItem = new Item();
        newItem.setProductname("Product 1");
        newItem.setAmount(1);
        newItem.setProductPrice(BigDecimal.valueOf(10.0));

        when(itemRepository.findByUsername(username)).thenReturn(new ArrayList<>());

        cartService.addItemToCart(newItem, username);

        ArgumentCaptor<Item> itemCaptor = ArgumentCaptor.forClass(Item.class);
        verify(itemRepository).save(itemCaptor.capture());
        Item savedItem = itemCaptor.getValue();
        assertEquals(newItem, savedItem);
    }

    @Test
    void addItemToCartExistingTest() {
        String username = "testUser";
        Item existingItem = new Item();
        UUID id = UUID.randomUUID();
        when(existingItem.getId()).thenReturn(id);
        existingItem.setProductname("Product 1");
        existingItem.setAmount(2);
        existingItem.setProductPrice(BigDecimal.valueOf(10.0));

        Item newItem = new Item();
        when(newItem.getId()).thenReturn(id);
        newItem.setProductname("Product 1");
        newItem.setAmount(1);
        newItem.setProductPrice(BigDecimal.valueOf(10.0));

        when(itemRepository.findByUsername(username)).thenReturn(Arrays.asList(existingItem));

        cartService.addItemToCart(newItem, username);

        ArgumentCaptor<Item> itemCaptor = ArgumentCaptor.forClass(Item.class);
        verify(itemRepository).save(itemCaptor.capture());
        Item savedItem = itemCaptor.getValue();
        assertEquals(3, savedItem.getAmount());
    }

    @Test
    public void removeAllItemTest() {
        String username = "testUser";
        Item item1 = new Item();
        item1.setProductname("product1");
        item1.setAmount(1);
        item1.setUsername(username);
        item1.setProductPrice(BigDecimal.valueOf(10.0));

        Item item2 = new Item();
        UUID id1 = UUID.randomUUID();
        when(item2.getProductId()).thenReturn(id1);
        item2.setProductname("product2");
        item2.setAmount(2);
        item2.setUsername(username);
        item2.setProductPrice(BigDecimal.valueOf(20.0));

        when(itemRepository.findByUsername(username)).thenReturn(Arrays.asList(item1, item2));

        cartService.removeAllItem(username);

        verify(itemRepository).deleteAll(Arrays.asList(item1, item2));
    }

    @Test
    void testGetAmountDifferenceOfItem() throws ItemNotFoundException {
        Item currentItem = new Item();
        UUID id = UUID.randomUUID();
        when(currentItem.getId()).thenReturn(id);
        currentItem.setAmount(3);
        Item toUpdateItem = new Item();
        //Mockito.when(toUpdateItem.getId()).thenReturn(id);
        toUpdateItem.setAmount(5);

        when(itemRepository.findById(currentItem.getId())).thenReturn(Optional.of(currentItem));

        int amountDifference = cartService.getAmountDifferenceOfItem(toUpdateItem);
        assertEquals(2, amountDifference);
    }

    @Test
    void testGetAmountDifferenceOfItemNotFound() {
        Item item = new Item();
        UUID id = UUID.randomUUID();
        item.setId(id);

        when(itemRepository.findById(item.getId())).thenReturn(Optional.empty());   //Todo

        assertThrows(ItemNotFoundException.class, () -> cartService.getAmountDifferenceOfItem(item));
    }

}
