package de.htwberlin.orderService.core.domain.services.impl;

import de.htwberlin.orderService.core.domain.model.TotalAmount;
import de.htwberlin.orderService.core.domain.services.interfaces.ITotalAmountRepository;
import de.htwberlin.orderService.core.domain.services.interfaces.ITotalAmountService;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class TotalAmountService implements ITotalAmountService {
    private final ITotalAmountRepository totalAmountRepository;

    public TotalAmountService(ITotalAmountRepository totalAmountRepository) {
        this.totalAmountRepository = totalAmountRepository;
    }

    @Override
    public TotalAmount createTotalAmount(TotalAmount totalAmount, UUID orderId) {
        totalAmount.setOrderId(orderId);
        return totalAmountRepository.save(totalAmount);

    }

    @Override
    public TotalAmount getTotalAmountbyOrderId(UUID id) {
        return totalAmountRepository.getByOrderId(id);
    }
}
