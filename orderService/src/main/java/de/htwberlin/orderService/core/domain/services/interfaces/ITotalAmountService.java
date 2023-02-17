package de.htwberlin.orderService.core.domain.services.interfaces;

import de.htwberlin.orderService.core.domain.model.TotalAmount;

import java.util.UUID;

public interface ITotalAmountService {
    public TotalAmount createTotalAmount(TotalAmount totalAmount, UUID orderId);

    public TotalAmount getTotalAmountbyOrderId(UUID id);
}
