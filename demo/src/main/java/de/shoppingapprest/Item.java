package de.shoppingapprest;

public class Item {
    private String name;
    private int amount;

    public Item(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public Item() {
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

    @Override
    public String toString() {
        return "Item [amount=" + amount + ", name=" + name + "]";
    }

}
