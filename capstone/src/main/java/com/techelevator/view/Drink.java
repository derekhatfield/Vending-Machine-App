package com.techelevator.view;

import java.math.BigDecimal;

public class Drink extends Item{

    private String message = "GLUG GLUG, YUM!";

    public Drink(String slotID, String name, BigDecimal price) {
        super(slotID, name, price);
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
