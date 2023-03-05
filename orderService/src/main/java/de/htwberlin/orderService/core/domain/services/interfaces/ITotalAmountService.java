package de.htwberlin.orderService.core.domain.services.interfaces;

import de.htwberlin.orderService.core.domain.model.TotalAmount;

import java.util.UUID;

public interface ITotalAmountService {
    public TotalAmount createTotalAmount(TotalAmount totalAmount);

    public TotalAmount getTotalAmountbyOrderId(UUID id);
}
