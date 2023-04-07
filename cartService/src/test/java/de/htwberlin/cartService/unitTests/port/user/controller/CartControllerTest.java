package de.htwberlin.cartService.unitTests.port.user.controller;

import de.htwberlin.cartService.core.domain.services.interfaces.ICartService;
import de.htwberlin.cartService.port.producer.CartProducer;
import de.htwberlin.cartService.port.user.controller.CartController;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CartControllerTest {

    @Mock
    private ICartService cartService;

    @Mock
    private CartProducer cartProducer;

    @InjectMocks
    private CartController cartController;

}
