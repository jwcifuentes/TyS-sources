package co.com.mintrabajo.infrastructure.security.jwtserviceprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages={"co.com.mintrabajo.infrastructure.security"})
@SpringBootApplication
public class JwtServiceProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtServiceProviderApplication.class, args);
	}
}
