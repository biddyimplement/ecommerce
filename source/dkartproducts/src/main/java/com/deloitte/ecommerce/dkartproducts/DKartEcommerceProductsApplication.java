package com.deloitte.ecommerce.dkartproducts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync
@ComponentScan(basePackages = { "com.deloitte" })
@PropertySources({
@PropertySource(value = "${spring.config.location}")
})
public class DKartEcommerceProductsApplication extends SpringBootServletInitializer{

	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DKartEcommerceProductsApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DKartEcommerceProductsApplication.class, args);

	}
}
