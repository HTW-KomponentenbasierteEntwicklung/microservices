package de.htwberlin.productService;

import de.htwberlin.productService.core.domain.model.Category;
import de.htwberlin.productService.core.domain.model.Color;
import de.htwberlin.productService.core.domain.model.Material;
import de.htwberlin.productService.core.domain.model.Product;
import de.htwberlin.productService.port.product.user.controller.ProductController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class FinalWebshopApplication {

	/*private static final Logger log = LoggerFactory.getLogger(FinalWebshopApplication.class);*/

	public static void main(String[] args) {
		SpringApplication.run(FinalWebshopApplication.class, args);
	}

	/*@Bean
	public CommandLineRunner demo(ProductController productController) {

		return args -> {
			Product testProduct1 = new Product("Oktopus Chilly", "Best oktopus to wrap around you", new BigDecimal(100), "https://cdn.shopify.com/s/files/1/1180/3390/products/03122021-Wooden-Hand-065-2.png?v=1676458620", Category.OCEAN, Material.BAUMWOLLE, Color.GREY, "50cm", 5);
			Product testProduct2 = new Product("Oktopi Colores", "A funny looking dude for your birthday!", new BigDecimal(200),"https://cdn.shopify.com/s/files/1/1180/3390/products/01092022-Octopus-1_540x.jpg?v=1664456185", Category.OCEAN, Material.BAUMWOLLE, Color.COLORFUL, "30cm", 2);
			Product testProduct3 = new Product("Whale Fluff", "Warm dude for the winter", new BigDecimal(300), "https://cdn.shopify.com/s/files/1/1180/3390/products/MW-01-1.jpg?v=1676542850", Category.OCEAN, Material.FLUFFY, Color.ROSE, "25cm", 10);


			productController.createProduct(testProduct1);
			productController.createProduct(testProduct2);
			productController.createProduct(testProduct3);

		};
	}*/
}
