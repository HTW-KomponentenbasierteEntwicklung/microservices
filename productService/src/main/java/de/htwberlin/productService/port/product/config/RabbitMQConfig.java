package de.htwberlin.productService.port.product.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class RabbitMQConfig {   //Todo: ggf. Klasse komplett löschen und cart Service zuerst starten?

    @Value("cartToProduct")
    private String cartToProductQueue;

    @Value("payment_exchange")
    private String exchange;

    @Value("email_payment_confirmation")
    private String routingKey;

    @Bean
    public Queue queue(){
        return new Queue(cartToProductQueue);
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
