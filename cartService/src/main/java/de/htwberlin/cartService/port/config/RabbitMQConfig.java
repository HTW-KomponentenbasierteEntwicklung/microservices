package de.htwberlin.cartService.port.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("cartToProduct")
    private String productQueue;
    
    @Value("orderToCart")
    private String orderToCartQueue;

    @Value("cart_exchange")
    private String exchange;

    @Value("cart_ToProduct")
    private String routingKey;

    @Bean
    public Queue queue(){
        return new Queue(productQueue);
    }
    @Bean
    public Queue orderToCartQueue(){
        return new Queue(orderToCartQueue);
    }


    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingKey);
    }


}
