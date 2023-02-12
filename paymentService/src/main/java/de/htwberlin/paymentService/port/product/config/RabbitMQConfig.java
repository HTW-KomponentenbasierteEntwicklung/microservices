package de.htwberlin.paymentService.port.product.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("emailPaymentConfirmation")
    private String queueEmailPaymentConfirmation;

    @Value("payment_exchange")
    private String exchange;

    @Value("email.payment.confirmation")
    private String routingKey;

    @Bean
    public Queue queue(){
        return new Queue(queueEmailPaymentConfirmation);
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
