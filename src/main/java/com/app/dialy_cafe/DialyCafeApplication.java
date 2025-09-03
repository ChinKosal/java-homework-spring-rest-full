package com.app.dialy_cafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication
public class DialyCafeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DialyCafeApplication.class, args);
	}

}
