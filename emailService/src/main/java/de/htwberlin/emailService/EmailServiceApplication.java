package de.htwberlin.emailService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmailServiceApplication {
	//private static final Logger log = LoggerFactory.getLogger(EmailServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EmailServiceApplication.class, args);
	}

	/*
	@Bean
	public CommandLineRunner demo(EmailService emailService) {

		return args -> {
			UUID userid = UUID.randomUUID();
			emailService.createOrderEmail("user", "mylinh.dao@t-online.de", userid);
		};
	}
	 */
}
