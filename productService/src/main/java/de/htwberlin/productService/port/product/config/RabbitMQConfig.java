package de.htwberlin.productService.port.product.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("cart-to-product")
    private String cartToProductQueue;

    @Value("product_exchange")
    private String exchange;

    @Value("product_routing_key")
    private String productRoutingKey;

    @Bean
    public Queue cartToProductQueue(){
        return new Queue(cartToProductQueue);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder
                .bind(cartToProductQueue())
                .to(exchange())
                .with(productRoutingKey);
    }


}
