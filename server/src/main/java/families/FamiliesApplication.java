package families;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A Spring Boot application servicing as a component with REST services
 * for a families archive. These services should be used by a Angular Front End component.
 */
@SpringBootApplication
public class FamiliesApplication {

	public static void main(String[] args) {
		SpringApplication.run(FamiliesApplication.class, args);
	}
}
