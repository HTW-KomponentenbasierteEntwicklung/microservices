package de.htwberlin.orderService;

import de.htwberlin.orderService.core.domain.model.Item;
import de.htwberlin.orderService.core.domain.model.Order;
import de.htwberlin.orderService.port.user.controller.OrderController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(OrderController orderController) {

		return args -> {


		};
	}

}
