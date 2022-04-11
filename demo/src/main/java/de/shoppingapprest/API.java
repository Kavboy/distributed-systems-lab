package de.shoppingapprest;

import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@SpringBootApplication
public class API {
	public static void main(String[] args) {
		SpringApplication.run(API.class, args);
	}

	final Logger logger = LoggerFactory.getLogger(de.shoppingapprest.API.class);

	@Value("${HOSTNAME:not_found}")
	String hostname;

	@RestController
	class HandleShoppingCart {

		private ShoppingCart cart = new ShoppingCart();

		@Operation(summary = "Get complete shoppingcart")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200", description = "Shoppingcart returned", content = @Content) })
		@ResponseStatus(HttpStatus.OK)
		@GetMapping(value = "/shoppingcart", produces = "application/json")
		public ShoppingCart getList() {
			return cart;
		}

		@Operation(summary = "Create a new shopping item")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "201", description = "Item created", content = @Content) })
		@ResponseStatus(HttpStatus.CREATED)
		@PostMapping(consumes = "application/json", produces = "application/json")
		public Item addItem(@RequestBody Item item) {
			logger.info("Received POST on instance " + hostname + " Item: " + item);

			this.cart.addToCart(item);

			return item;

		}

		@Operation(summary = "Get item in shoppingcart")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200", description = "item found", content = @Content) })
		@ResponseStatus(HttpStatus.OK)
		@GetMapping(value = "shoppingcart/{itemname}", produces = "application/json")
		public Item getItem(@PathVariable String itemname) {
			return this.cart.getItem(itemname);
		}

	}
}
