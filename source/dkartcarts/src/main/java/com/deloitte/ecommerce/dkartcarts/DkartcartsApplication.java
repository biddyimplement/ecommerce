package com.deloitte.ecommerce.dkartcarts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;


@SpringBootApplication
@ComponentScan(basePackages = { "com.deloitte" })
@PropertySources({
@PropertySource(value = "${spring.config.location}")
})
public class DkartcartsApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DkartcartsApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DkartcartsApplication.class, args);
	}
}
