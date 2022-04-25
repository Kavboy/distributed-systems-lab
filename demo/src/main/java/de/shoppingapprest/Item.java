package de.shoppingapprest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "amount")
    private int amount;

    @ManyToOne(targetEntity = ShoppingCart.class, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "shoppingCart_id", nullable = false)
    @JsonIgnore
    private ShoppingCart shoppingCart;

    public Item(String name, int amount, ShoppingCart shoppingCart) {
        this.name = name;
        this.amount = amount;
        this.shoppingCart = shoppingCart;
    }

    public Item() {
        super();
    }

    public Item(String name) {
        this.name = name;
        this.amount = 1;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ShoppingCart getShoppingCart() {
        return this.shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", amount=" + amount + ", name=" + name + "]";
    }

}
