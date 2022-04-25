package de.shoppingapprest.repositorys;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.shoppingapprest.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {
    Item findByName(String name);
}
