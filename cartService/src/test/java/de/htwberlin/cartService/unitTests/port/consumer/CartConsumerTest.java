package de.htwberlin.cartService.unitTests.port.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.htwberlin.cartService.core.domain.model.Cart;
import de.htwberlin.cartService.core.domain.model.Item;
import de.htwberlin.cartService.core.domain.services.interfaces.ICartService;
import de.htwberlin.cartService.port.consumer.CartConsumer;
import de.htwberlin.cartService.port.dto.OrderDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
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

    @Test
    public void testConsumeInvalidMessageFormat() throws JsonProcessingException {
        cartConsumer = new CartConsumer();
        cartConsumer.setCartService(cartService);

        String invalidMessage = "not a valid message";

        assertThrows(RuntimeException.class, () -> cartConsumer.consume(invalidMessage));
    }

    @Test
    public void testConsumeNullMessage() throws JsonProcessingException {
        cartConsumer = new CartConsumer();
        cartConsumer.setCartService(cartService);

        String nullMessage = null;

        assertThrows(RuntimeException.class, () -> cartConsumer.consume(nullMessage));
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

    @Test // Todo: Nachdem Service klasse getestet ist
    public void testConsumeValidFullCart() throws JsonProcessingException {
        cartConsumer = new CartConsumer();
        cartConsumer.setCartService(cartService);

        String username = "testuser";
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUsername(username);

        Item item1 = new Item();
        item1.setProductname("Knut");

        List<Item> items = new ArrayList<>();
        items.add(item1);

        orderDTO.setUsername(username);
        String message = new ObjectMapper().writeValueAsString(orderDTO);
        Cart cart = new Cart(items, BigDecimal.valueOf(100.0));

        Mockito.when(cartService.addItemToCart(item1, username)).thenReturn(cart);
        Mockito.when(cartService.getCartForUsername(username)).thenReturn(cart);

        assertFalse(cart.getItems().isEmpty());

        cartConsumer.consume(message);

        cart = cartService.getCartForUsername(username);
        System.out.println(cart.getItems().toString());
        assertTrue(cart.getItems().isEmpty());  //Todo
    }
}
