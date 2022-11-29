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

    @PostMapping("/api/auth/signup")
	public String signup(@RequestBody UserModel user_json) {
        User user = new User();
        return user.store(user_json);
	}

	@GetMapping("/api/auth/signin")
	public String signin(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
		Token token = new Token();
		String auth = authorization.substring(6);
		return token.basicAuth(auth);
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
	public String createTask(
		@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
		@RequestBody TaskModel task_json
	) {
		Task task = new Task();
		return task.store(authorization, task_json);
	}

	@GetMapping("/api/task/delete/{task_id}")
	public boolean deleteTask(
		@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
		@PathVariable String task_id
	) {
		Task task = new Task();
		return task.destroy(authorization, task_id);
	}

    @PostMapping("/api/task/update/{task_id}")
	public boolean updateTask(
		@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
		@RequestBody TaskModel task_json,
		@PathVariable String task_id
	) {
		Task task = new Task();
		return task.update(authorization, task_json, task_id);
	}
}
