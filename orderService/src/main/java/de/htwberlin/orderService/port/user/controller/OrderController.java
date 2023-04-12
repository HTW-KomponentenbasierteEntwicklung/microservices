package de.htwberlin.orderService.port.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.htwberlin.orderService.OrderServiceApplication;
import de.htwberlin.orderService.core.domain.model.Order;
import de.htwberlin.orderService.core.domain.model.OrderRegistry;
import de.htwberlin.orderService.core.domain.services.exception.NotFoundByOrderIdException;
import de.htwberlin.orderService.core.domain.services.interfaces.IOrderService;
import de.htwberlin.orderService.port.dto.OrderDTO;
import de.htwberlin.orderService.port.dtoMapper.OrderDTOMapper;
import de.htwberlin.orderService.port.rabbitProducer.OrderProducer;
import de.htwberlin.orderService.port.user.exception.OrderNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(OrderServiceApplication.class);

    @Autowired
    private IOrderService orderService;
    @Autowired
    private OrderProducer orderProducer;

    @PostMapping(path = "/order")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Order createOrder(@RequestBody Order order, @RequestParam String username) {
        Order createdOrder = orderService.createOrder(order,username, new Date());
        orderProducer.sendToAll(createdOrder);
        return createdOrder;
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Order getOrderById(@PathVariable("id") UUID id) throws OrderNotFoundException {
        Order order = null;
        try {
            order = orderService.getOrderByOrderId(id);
        }catch (NotFoundByOrderIdException e){
            throw new OrderNotFoundException(id);
        }
        return order;
    }
    @GetMapping("/username/{username}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<OrderRegistry> findByUsername(@PathVariable("username") String username) {
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
