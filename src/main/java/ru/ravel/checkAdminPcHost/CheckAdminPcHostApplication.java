package ru.ravel.checkAdminPcHost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CheckAdminPcHostApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(CheckAdminPcHostApplication.class, args);
	}

}
