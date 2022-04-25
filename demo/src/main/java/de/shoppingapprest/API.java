package de.shoppingapprest;

import org.springframework.http.HttpStatus;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.shoppingapprest.repositorys.ItemRepository;
import de.shoppingapprest.repositorys.ShoppingCartRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
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

		@Autowired
		ShoppingCartRepository shoppingCartRepository;

		@Autowired
		ItemRepository itemRepository;

		@Operation(summary = "Create shoppingcart")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "201", description = "Shoppingcart created", content = @Content) })
		@ResponseStatus(HttpStatus.OK)
		@PostMapping(value = "/shoppingcarts", produces = "application/json")
		public ShoppingCart createShoppingCart() {
			return shoppingCartRepository.save(new ShoppingCart());
		}

		@Operation(summary = "Get all shoppingcarts")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200", description = "Shoppingcart returned", content = @Content) })
		@ResponseStatus(HttpStatus.OK)
		@GetMapping(value = "/shoppingcarts", produces = "application/json")
		public Iterable<ShoppingCart> getList() {
			return shoppingCartRepository.findAll();
		}

		@Operation(summary = "Get specific shoppingcart")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200", description = "Shoppingcart returned", content = @Content) })
		@ResponseStatus(HttpStatus.OK)
		@GetMapping(value = "/shoppingcarts/{id}", produces = "application/json")
		public Optional<ShoppingCart> getShoppingCart(@PathVariable Long id) {
			return shoppingCartRepository.findById(id);
		}

		@Operation(summary = "Create a new shopping item")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "201", description = "Item created", content = @Content) })
		@ResponseStatus(HttpStatus.CREATED)
		@PostMapping(value = "shoppingcart/{id}", consumes = "application/json", produces = "application/json")
		public Item addItem(@PathVariable long id, @RequestBody Item item) {
			logger.info("Received POST on instance " + hostname + " Item: " + item);
			Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findById(id);
			ShoppingCart shoppingCart = shoppingCartOptional.get();

			Item saveItem = itemRepository.save(new Item(item.getName(), item.getAmount(), shoppingCart));

			shoppingCart.getCart().add(saveItem);

			logger.info("Added item to cart: " + shoppingCart);

			return saveItem;

		}

		@Operation(summary = "Get all items")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200", description = "Items returned", content = @Content) })
		@ResponseStatus(HttpStatus.OK)
		@GetMapping(value = "/items", produces = "application/json")
		public Iterable<Item> getItemList() {
			return itemRepository.findAll();
		}

		@Operation(summary = "Get item in shoppingcart")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200", description = "item found", content = @Content) })
		@ResponseStatus(HttpStatus.OK)
		@GetMapping(value = "shoppingcart/{id}/{itemname}", produces = "application/json")
		public Item getItem(@PathVariable long id, @PathVariable String itemname) {
			ShoppingCart shoppingCart = shoppingCartRepository.findById(id).get();
			return shoppingCart.getItem(itemname);
		}

	}
}
