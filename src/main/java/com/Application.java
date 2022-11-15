package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import core.*;
import classes.*;

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

	@PostMapping("/api/auth/signup")
	public int signup(@RequestBody UserModel user_json) {
        User user = new User();
        return user.store(user_json);
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

    @GetMapping("/api/task/")
	public Task[] getAll(
		@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization
	) {
		Task task = new Task();
		return task.getAll(authorization);
	}

	@PostMapping("/api/task/create")
	public int createTask(
		@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
		@RequestBody TaskModel task_json
	) {
		Task task = new Task();
		return task.store(authorization, task_json);
	}

	@GetMapping("/api/task/delete/{task_id}")
	public boolean deleteTask(
		@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
		@PathVariable int task_id
	) {
		Task task = new Task();
		return task.destroy(authorization, task_id);
	}

    @PostMapping("/api/task/update/{task_id}")
	public boolean updateTask(
		@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
		@PathVariable TaskModel task_json
	) {
		Task task = new Task();
		return task.update(authorization, task_json);
	}
}
