package dev.gustavosdaniel.livrariaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // PARA QUE A DATA SEJA INSERIDA AUTOIMATICAMENTE
@SpringBootApplication
public class LivrariaapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivrariaapiApplication.class, args);
	}

}
