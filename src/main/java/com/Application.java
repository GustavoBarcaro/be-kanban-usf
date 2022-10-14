package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import core.Database;
import com.Teste;

@SpringBootApplication
@RestController
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		Database database = new Database();
		System.out.println(database.connect());
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

    @GetMapping("/teste/{id}")
    public Teste read(@PathVariable String id) {
		Teste teste = new Teste(id);
        return teste;
    }

	@GetMapping("/api/auth/signup")
	public boolean signup() {
		return true;
	}

	@GetMapping("/api/auth/signin")
	public boolean signin() {
		return true;
	}

	@GetMapping("/api/auth/signout")
	public boolean signout() {
		return true;
	}

	@GetMapping("/api/test/all")
	public boolean all() {
		return true;
	}

	@GetMapping("/api/test/user")
	public boolean user() {
		return true;
	}

	@GetMapping("/api/test/mod")
	public boolean mod() {
		return true;
	}

	@GetMapping("/api/test/admin")
	public boolean admin() {
		return true;
	}

}
