package de.htwberlin.orderService.port.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.htwberlin.orderService.core.domain.model.Item;
import de.htwberlin.orderService.core.domain.model.Order;
import de.htwberlin.orderService.core.domain.model.OrderRegistry;
import de.htwberlin.orderService.core.domain.services.exception.OrderNotFoundServicesException;
import de.htwberlin.orderService.core.domain.services.interfaces.IItemService;
import de.htwberlin.orderService.core.domain.services.interfaces.IOrderService;
import de.htwberlin.orderService.port.dto.OrderDTO;
import de.htwberlin.orderService.port.dtoMapper.OrderDTOMapper;
import de.htwberlin.orderService.port.rabbitProducer.OrderProducer;
import de.htwberlin.orderService.port.user.exception.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private OrderProducer orderProducer;
    @Autowired
    private OrderDTOMapper orderDTOMapper;

    @PostMapping(path = "/order")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody UUID createOrder(@RequestBody Order order, @RequestHeader (HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String username = getusernameFromRequestHeader(authorizationHeader);

        Order createdOrder = orderService.createOrder(order,username, new Date());
        OrderDTO orderDTO= orderDTOMapper.getMappedOrderDTO(createdOrder);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String message = objectMapper.writeValueAsString(orderDTO);
            orderProducer.sendToAll(message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return orderDTO.getOrderNr();
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Order getOrderById(@PathVariable UUID id) throws OrderNotFoundException {
        Order order = null;
        try {
            order = orderService.getOrderbyid(id);
        }catch (OrderNotFoundServicesException e){
            throw new OrderNotFoundException(id);
        }
        return order;
    }
    @GetMapping("/username/{username}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<OrderRegistry> findByUsername(@PathVariable String username) {
        return orderService.getOrderRegistryByUsername(username);
    }
    public String getusernameFromRequestHeader(String authorizationHeader){
        String accessToken = authorizationHeader.split(" ", 2)[1];
        String payloadEncoded = accessToken.split("\\.")[1];

        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payloadDecoded = new String(decoder.decode(payloadEncoded));
        JSONObject payloadJSON = new JSONObject(payloadDecoded);
        String username = payloadJSON.getString("preferred_username");
        return username;

    }
}
