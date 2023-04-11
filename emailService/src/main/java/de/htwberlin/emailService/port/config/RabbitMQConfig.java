package de.htwberlin.emailService.port.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {


    @Value("paymentToEmail")
    private String paymentQueue;
    @Value("self")
    private String selfQueue;

    @Value("order_exchange")
    private String exchange;

    @Value("order.ToEmail")
    private String emailRoutingKey;

    @Value(("self_routing_key"))
    private String selfRoutingKey;

    @Bean
    public Queue emailQueue(){
        return new Queue(paymentQueue);
    }
    @Bean
    public Queue selfQueue(){
        return new Queue(selfQueue);
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

}
