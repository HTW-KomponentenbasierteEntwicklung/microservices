package de.htwberlin.paymentService;

import de.htwberlin.paymentService.core.domain.model.PaymentMethod;
import de.htwberlin.paymentService.core.domain.model.PaymentStatus;
import de.htwberlin.paymentService.port.product.user.controller.PaymentController;
import de.htwberlin.paymentService.core.domain.model.Payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.awt.*;
import java.math.BigDecimal;
import java.util.UUID;

@SpringBootApplication
public class PaymentServiceApplication {
	private static final Logger log = LoggerFactory.getLogger(PaymentServiceApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(PaymentController paymentController) {

		return args -> {
			Payment testPayment = new Payment("12a", "mylinh", "payer", new BigDecimal(100), PaymentStatus.PENDING, PaymentMethod.VORKASSE);

			Payment createdP = paymentController.create(testPayment);
			log.info(createdP.getId().toString());

		};
	}

}
