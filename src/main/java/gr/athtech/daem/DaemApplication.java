package gr.athtech.daem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DaemApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaemApplication.class, args);
	}

}
