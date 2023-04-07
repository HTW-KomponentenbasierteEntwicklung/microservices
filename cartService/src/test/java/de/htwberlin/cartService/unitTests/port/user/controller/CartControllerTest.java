package de.htwberlin.cartService.unitTests.port.user.controller;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CartControllerTest {

    @Mock
    private IPaymentService paymentService;

    @Mock
    private PaymentProducer paymentProducer;

    @InjectMocks
    private PaymentController paymentController;

}
