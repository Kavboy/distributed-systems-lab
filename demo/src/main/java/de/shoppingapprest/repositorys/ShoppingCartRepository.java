package de.shoppingapprest.repositorys;

import org.springframework.data.repository.CrudRepository;

import de.shoppingapprest.ShoppingCart;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {
}
