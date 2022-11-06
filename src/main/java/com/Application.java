package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import core.Database;
import core.Token;

import com.Teste;

import classes.Task;
import classes.User;

@SpringBootApplication
@RestController
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
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
	public Token signin(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
		Token token = new Token();
		String auth = authorization.substring(6);
		token.basicAuth(auth);
		return token;
	}

	@GetMapping("/api/auth/signout")
	public boolean signout(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
		Token token = new Token();
		token.destroy(authorization);
		return true;
	}

	@GetMapping("/api/task/create")
	public boolean createTask(
		@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
		@RequestBody Task idk
	) {
		Task task = new Task();
		task.store(authorization, idk);
		return true;
	}
}
