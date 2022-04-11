package de.shoppingapprest;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Item> cart;

    public ShoppingCart() {
        this.cart = new ArrayList<Item>();
    }

    public List<Item> getCart() {
        return this.cart;
    }

    public Item getItem(String name) {

        for (int i = 0; i < this.cart.size(); i++) {
            if (this.cart.get(i).getName() == name) {
                return this.cart.get(i);
            }
        }

        return null;
    }

    public void addToCart(Item item) {
        this.cart.add(item);
    }

    public void removeFromCart(Item item) {
        this.cart.remove(item);
    }

}
