package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RestController
	class Hello {

		@GetMapping("/")
		String helloWorld() {
			return "Hello Esslingen SS22";
		}

		@GetMapping("/greeting/{name}")
		String greeting(@PathVariable String name) {
			return "Hello " + name;
		}

	}

	@RestController
	class Listhandling {

		private List<String> items = new ArrayList<String>();

		@GetMapping("/items")
		List<String> getItems() {
			return items;
		}

		@PostMapping("/items")
		Boolean greeting(@RequestBody String item) {
			return items.add(item);
		}

	}

}
