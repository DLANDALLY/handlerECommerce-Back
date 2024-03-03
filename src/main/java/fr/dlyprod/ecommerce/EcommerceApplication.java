package fr.dlyprod.ecommerce;

import fr.dlyprod.ecommerce.services.DataGenerationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class EcommerceApplication {

	private DataGenerationService dataGenerationService;

	public EcommerceApplication(DataGenerationService dataGenerationService) {
		this.dataGenerationService = dataGenerationService;
	}

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@EventListener(ContextRefreshedEvent.class)
	public void getFictives() {
		dataGenerationService.getFakerData(10);
		dataGenerationService.getDataArticle(10);
		dataGenerationService.getDataUser(10);
		dataGenerationService.getDataCart(10);
		//dataGenerationService.getCategorie(10);
	}
}
