package fr.eclipseteam.scrimer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScrimerApplication {

	public static void main(String[] args) {
		App myApp = new App();
		SpringApplication.run(ScrimerApplication.class, args);
	}

}
