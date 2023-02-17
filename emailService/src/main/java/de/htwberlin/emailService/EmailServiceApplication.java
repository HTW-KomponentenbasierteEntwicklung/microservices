package de.htwberlin.emailService;

import de.htwberlin.emailService.core.model.EmailAdress;
import de.htwberlin.emailService.core.service.impl.EmailService;
import de.htwberlin.emailService.core.service.interfaces.IEmailAdressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.UUID;

@SpringBootApplication
public class EmailServiceApplication {
	private static final Logger log = LoggerFactory.getLogger(EmailServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EmailServiceApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(EmailService emailService) {

		return args -> {
			UUID userid = UUID.randomUUID();
			emailService.createEmailAdress(userid,"mylinh","mylinh.dao@t-online.de");

		};
	}
}
