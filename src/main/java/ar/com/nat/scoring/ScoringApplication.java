package ar.com.nat.scoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//@Configuration
//@PropertySource("file:c:/opt/config/guay/application.properties")
public class ScoringApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ScoringApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(ScoringApplication.class, args);
	}
}
