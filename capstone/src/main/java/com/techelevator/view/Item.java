package com.techelevator.view;

import java.math.BigDecimal;

public class Item {

    private String slotID;
    private String name;
    private BigDecimal price;
    private String message = "";
    private int stock;
    private int runningTotal;

    public Item(String slotID, String name, BigDecimal price) {
        this.slotID = slotID;
        this.name = name;
        this.price = price;
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

    public int getRunningTotal() {
        return runningTotal;
    }

    public void vendItem(Item itemToVend) {
        this.stock--;
        this.runningTotal++;
    }
}
