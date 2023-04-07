package de.htwberlin.cartService.unitTests.port.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.htwberlin.cartService.core.domain.model.Cart;
import de.htwberlin.cartService.core.domain.model.Item;
import de.htwberlin.cartService.core.domain.services.interfaces.ICartService;
import de.htwberlin.cartService.port.consumer.CartConsumer;
import de.htwberlin.cartService.port.dto.OrderDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CartConsumerTest {

    @Mock
    private ICartService cartService;

    @InjectMocks
    private CartConsumer cartConsumer;

    @Test
    public void testConsumeValid() throws JsonProcessingException {
        cartConsumer = new CartConsumer();
        cartConsumer.setCartService(cartService);

        String username = "testuser";
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUsername(username);
        String message = new ObjectMapper().writeValueAsString(orderDTO);

        cartConsumer.consume(message);

        verify(cartService).removeAllItem(username);
    }

    @Test(expected = RuntimeException.class)
    public void testConsumeInvalidMessageFormat() throws JsonProcessingException {
        cartConsumer = new CartConsumer();
        cartConsumer.setCartService(cartService);

        String invalidMessage = "not a valid message";

        cartConsumer.consume(invalidMessage);
    }

    @Test(expected = RuntimeException.class)
    public void testConsumeNullMessage() throws JsonProcessingException {
        cartConsumer = new CartConsumer();
        cartConsumer.setCartService(cartService);

        String nullMessage = null;

        cartConsumer.consume(nullMessage);
    }

    @Test
    public void testConsumeValidNonExistingUsername() throws JsonProcessingException {
        cartConsumer = new CartConsumer();
        cartConsumer.setCartService(cartService);

        String nonExistingUsername = "nonExistingUser";
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUsername(nonExistingUsername);
        String message = new ObjectMapper().writeValueAsString(orderDTO);

        cartConsumer.consume(message);

        verify(cartService).removeAllItem(nonExistingUsername);
    }

    @Test
    public void testConsumeValidEmptyUsername() throws JsonProcessingException {
        cartConsumer = new CartConsumer();
        cartConsumer.setCartService(cartService);

        String emptyUsername = "";
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUsername(emptyUsername);
        String message = new ObjectMapper().writeValueAsString(orderDTO);

        cartConsumer.consume(message);

        verify(cartService).removeAllItem(emptyUsername);
    }

    @Test
    public void testConsumeValidFullCart() throws JsonProcessingException {
        cartConsumer = new CartConsumer();
        cartConsumer.setCartService(cartService);

        String username = "testuser";
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUsername(username);


        Item item1 = new Item();
        item1.setProductname("Knut");

        Item item2 = new Item();
        item1.setProductname("Peggy");

        List<Item> cartItems = new ArrayList<>();
        cartItems.add(item1);
        cartItems.add(item2);

        orderDTO.setUsername(username);
        String message = new ObjectMapper().writeValueAsString(orderDTO);

        // add items to the cart
        for (Item item : cartItems) {
            cartService.addItemToCart(item, username);
        }

        // verify that the cart is not empty before consuming the message
        Cart cart = cartService.getCartForUsername(username);
        assertFalse(cart.getItems() == null);

        // consume the message
        cartConsumer.consume(message);

        // verify that the cart is now empty
        cart = cartService.getCartForUsername(username);
        assertTrue(cart.getItems() == null);
    }



    /*@Test
    public void testConsumeWithFullCart () throws JsonProcessingException {
        Item item1 = new Item();
        item1.setProductname("Knut");

        Item item2 = new Item();
        item1.setProductname("Peggy");
        String username = "alfred";
        cartService.addItemToCart(item1, username);
        cartService.addItemToCart(item2, username);

        // Order kommt rein
        OrderDTO orderDTO = new OrderDTO();
        UUID orderId = UUID.randomUUID();
        orderDTO.setOrderId(orderId);
        orderDTO.setUsername(username);
        orderDTO.setTotalAmount(BigDecimal.valueOf(100.0));

        ObjectMapper objectMapper = new ObjectMapper();
        String message = objectMapper.writeValueAsString(orderDTO);
        cartConsumer.consume(message);

        // lösche alle Items aus Cart wo bei Cart und User der gleiche Username ist
        List<Item> items = new LinkedList<>();
        Cart cartExpected = new Cart(items, BigDecimal.ZERO);
        assertEquals(cartExpected, cartService.getCartForUsername(username));
    }*/
}
