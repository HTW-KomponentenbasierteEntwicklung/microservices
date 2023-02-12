package de.htwberlin.finalWebshop;

import de.htwberlin.finalWebshop.core.domain.model.Category;
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

import java.awt.*;
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
			Product testProduct1 = new Product("Testprodukt1", "Testprodukt1", new BigDecimal(100), new BigDecimal(100), "path", Category.SCHLAFEN, Material.BAUMWOLLE, Color.blue, "20cm", 1);
			Product testProduct2 = new Product("Testprodukt2", "Testprodukt2", new BigDecimal(200),"path", Category.SCHLAFEN, Material.BAUMWOLLE, Color.blue, "20cm", 1);
			Product testProduct3 = new Product("Testprodukt3", "Testprodukt3", new BigDecimal(300), new BigDecimal(400), "path", Category.SCHLAFEN, Material.BAUMWOLLE, Color.blue, "20cm", 1);

			Product productToTestUpdate = new Product("Testprodukt4", "Produkt um update zu testen", new BigDecimal(400), new BigDecimal(400), "path", Category.SCHLAFEN, Material.BAUMWOLLE, Color.blue, "20cm", 1);

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
			optionalProduct = productController.findProductsByName("Testprodukt3").get(0);
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
			productController.update(testProduct1.getId(), productToTestUpdate);
			log.info(String.format("Product updated with update(%s, productToTestUpdate):", testProduct1.getId()));
			log.info("Products found with getAllProducts() after testProduct1 was updated:");
			log.info("--------------------------------");
			for(Product product : productController.getAllProducts()) {
				log.info(product.toString());
			}
			log.info("");

			//get filtered products- funktioniert leider nicht bei mir, daher auskommentiert
			/*Sort filter = Sort.by(String.valueOf(Category.SCHLAFEN));
			productController.getAllProductsByFilter(filter);
			filter = Sort.by(String.valueOf(Color.red));
			productController.getAllProductsByFilter(filter);*/
		};
	}
}
