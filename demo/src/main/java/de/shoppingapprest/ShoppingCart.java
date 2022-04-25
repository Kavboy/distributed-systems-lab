package de.shoppingapprest;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ShoppingCart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy = "shoppingCart", targetEntity = Item.class, cascade = CascadeType.ALL)
    private List<Item> cart = new ArrayList<>();

    public ShoppingCart() {
        super();
    }

    public long getId() {
        return this.id;
    }

    public List<Item> getCart() {
        return this.cart;
    }

    public void setCart(List<Item> cart) {
        this.cart = cart;
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

    @Override
    public String toString() {
        String temp = "ShoppingCart [id=" + id + ", cart=[";

        for (Item item : this.cart) {
            temp += " " + item + ", ";
        }

        return temp + "]]";
    }

}
