package Back_endSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class BackEndConSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackEndConSpringBootApplication.class, args);
	}

}
