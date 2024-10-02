package name.abuchen.portfolio.web.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import name.abuchen.portfolio.model.ClientFactory;

@RestController
public class PpResource {

	
	@GetMapping("/pp")
	public String process() throws FileNotFoundException, IOException {
        var client = ClientFactory.load(new FileInputStream("/home/dan/kommer.xml"));

		
		
		return LocalDateTime.now().toString();
	}
}
