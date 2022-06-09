package com.techelevator.view;

public class Item implements Vendable {

    private String slotID;
    private String name;
    private double price;
    private String message;
    private int stock;

    public Item(String slotID, String name, double price, String message) {
        this.slotID = slotID;
        this.name = name;
        this.price = price;
        this.stock = 5;
        this.message = message;

    }

    @Override
    public String getSlotID() {
        return this.slotID;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public int getStock() {
        return this.stock;
    }

    @Override
    public void vendItem() {

    }
}
