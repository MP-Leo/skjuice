package br.com.skjuice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SkJuiceApplication {

	@Autowired
	@Qualifier("applicationName")
	private String applicationName;

	@GetMapping("/")
	public String helloWord(){
		return applicationName;
	}

	public static void main(String[] args) {
		SpringApplication.run(SkJuiceApplication.class, args);
	}

}
