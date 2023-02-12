package de.htwberlin.paymentService.core.domain.service.interfaces;

import de.htwberlin.paymentService.core.domain.model.Payment;
import de.htwberlin.paymentService.port.product.user.exception.*;
import java.util.UUID;

/**
 * The Interface defines methods that can be applied to a Product.
 */
public interface IPaymentService {

    /**
     * Creates a new payment and it's UUID.
     * @param payment the new payment
     * @return the new payment
     */
    Payment createPayment(Payment payment);

    /**
     * Overwrites a product when the Id was found.
     * Does not create a new product.
     * @param id payment Id
     * @param payment new Payment Information
     * @return the updated Payment
     * @throws PaymentNotFoundException when the Payment Id wasn't found
     */
    Payment updateProduct(UUID id, Payment payment) throws PaymentNotFoundException;

    /**
     * Finds a single Payment by it's Id.
     * @param id Id in UUID format
     * @return the Payment
     * @throws PaymentNotFoundException when Payment not found (null)
     */
    Payment getPaymentById(UUID id) throws PaymentNotFoundException;


}
