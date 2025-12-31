package com.spring.resturant;

import com.spring.resturant.sitting.TokenConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(TokenConfig.class)
@SpringBootApplication
public class ResturantApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResturantApplication.class, args);
	}

}
