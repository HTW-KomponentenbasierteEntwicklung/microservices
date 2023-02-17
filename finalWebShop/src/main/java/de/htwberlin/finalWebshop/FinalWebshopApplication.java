package de.htwberlin.finalWebshop;

import de.htwberlin.finalWebshop.core.domain.model.Category;
import de.htwberlin.finalWebshop.core.domain.model.Color;
import de.htwberlin.finalWebshop.core.domain.model.Material;
import de.htwberlin.finalWebshop.core.domain.model.Product;
import de.htwberlin.finalWebshop.port.product.user.controller.ProductController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;

@SpringBootApplication
public class FinalWebshopApplication {

	private static final Logger log = LoggerFactory.getLogger(FinalWebshopApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FinalWebshopApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ProductController productController) {

		return args -> {
			Product testProduct1 = new Product("Oktopus Chilly", "Best oktopus to wrap around you", new BigDecimal(100), "https://cdn.shopify.com/s/files/1/1180/3390/products/03122021-Wooden-Hand-065-2.png?v=1676458620", Category.OCEAN, Material.BAUMWOLLE, Color.GREY, "50cm", 5);
			Product testProduct2 = new Product("Oktopi Colores", "A funny looking dude for your birthday!", new BigDecimal(200),"https://cdn.shopify.com/s/files/1/1180/3390/products/01092022-Octopus-1_540x.jpg?v=1664456185", Category.OCEAN, Material.BAUMWOLLE, Color.COLORFUL, "30cm", 2);
			Product testProduct3 = new Product("Whale Fluff", "Warm dude for the winter", new BigDecimal(300), new BigDecimal(400), "https://cdn.shopify.com/s/files/1/1180/3390/products/MW-01-1.jpg?v=1676542850", Category.OCEAN, Material.FLUFFY, Color.ROSE, "25cm", 10);


			productController.create(testProduct1);
			productController.create(testProduct2);
			productController.create(testProduct3);

			// fetch all products
			log.info("");
			log.info("Products found with getAllProducts():");
			log.info("-------------------------------");
			for (Product product : productController.getAllProducts()) {
				log.info(product.toString());
			}
			log.info("");

			// fetch an individual product by ID
			Product optionalProduct = productController.getProductById(testProduct2.getId());
			log.info(String.format("Product found with getProduct(%s):", testProduct2.getId()));
			log.info("--------------------------------");
			log.info(optionalProduct.toString());
			log.info("");

			// fetch an individual product by name
			optionalProduct = productController.findProductsByName("Oktopus Chilly").get(0);
			log.info(String.format("Product found with getProductByName(%s):", testProduct3.getName()));
			log.info("--------------------------------");
			log.info(optionalProduct.toString());
			log.info("");

			// delete a product by ID
			productController.delete(testProduct2.getId());
			log.info(String.format("Product deleted with delete(%s):", testProduct2.getId()));
			log.info("Products found with getAllProducts() after testProduct2 was deleted:");
			log.info("-------------------------------");
			for(Product product : productController.getAllProducts()) {
				log.info(product.toString());
			}
			log.info("");

			// update a product
			/*
			productController.update(testProduct1.getId(), productToTestUpdate);
			log.info(String.format("Product updated with update(%s, productToTestUpdate):", testProduct1.getId()));
			log.info("Products found with getAllProducts() after testProduct1 was updated:");
			log.info("--------------------------------");
			for(Product product : productController.getAllProducts()) {
				log.info(product.toString());
			}
			log.info("");*/

			//get filtered products- funktioniert leider nicht bei mir, daher auskommentiert
			/*Sort filter = Sort.by(String.valueOf(Category.SCHLAFEN));
			productController.getAllProductsByFilter(filter);
			filter = Sort.by(String.valueOf(Color.red));
			productController.getAllProductsByFilter(filter);*/
		};
	}
}
