package com.techelevator.view;

import java.math.BigDecimal;

public class Item {

    private String slotID;
    private String name;
    private BigDecimal price;
    private String message;
    private int stock;

    public Item(String slotID, String name, BigDecimal price, String message) {
        this.slotID = slotID;
        this.name = name;
        this.price = price;
        this.message = message;
        this.stock = 5;
    }

    public String getSlotID() {
        return this.slotID;
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public String getMessage() {
        return this.message;
    }

    public int getStock() {
        return this.stock;
    }

    public void vendItem(Item itemToVend) {
        this.stock--;
    }
}
