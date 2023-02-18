package de.htwberlin.orderService.port.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("payment")
    private String paymentQueue;
    @Value("self")
    private String selfQueue;
    @Value("emailOrderConfirmation")
    private String emailQueue;
    @Value("cart")
    private String cartQueue;

    @Value("order_exchange")
    private String exchange;

    @Value("email_routing_key")
    private String emailRoutingKey;

    @Value("cart_routing_key")
    private String cartRoutingKey;
    @Value("payment_routing_key")
    private String paymentRoutingKey;
    @Value(("self_routing_key"))
    private String selfRoutingKey;

    @Bean
    public Queue emailQueue(){
        return new Queue(emailQueue);
    }
    @Bean
    public Queue selfQueue(){
        return new Queue(selfQueue);
    }
    @Bean
    public Queue cartQueue(){
        return new Queue(cartQueue);
    }
    @Bean
    public Queue paymentQueue(){
        return new Queue(paymentQueue);
    }


    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding emailBinding(){
        return BindingBuilder
                .bind(emailQueue())
                .to(exchange())
                .with(emailRoutingKey);
    }
    @Bean
    public Binding selfBinding(){
        return BindingBuilder
                .bind(selfQueue())
                .to(exchange())
                .with(selfRoutingKey);
    }
    @Bean
    public Binding paymentBinding(){
        return BindingBuilder
                .bind(paymentQueue())
                .to(exchange())
                .with(paymentRoutingKey);
    }
    @Bean
    public Binding cartBinding(){
        return BindingBuilder
                .bind(cartQueue())
                .to(exchange())
                .with(cartRoutingKey);
    }

}
