package fr.brilhante.joao.springbootaws.server.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/")
	public String getHello() {
		return "Hello, world!";
	}

}
