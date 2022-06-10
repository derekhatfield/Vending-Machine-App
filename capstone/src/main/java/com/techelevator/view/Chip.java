package com.techelevator.view;

import java.math.BigDecimal;

public class Chip extends Item {

    private String message = "CRUNCH CRUNCH, YUM!";

    public Chip(String slotID, String name, BigDecimal price) {
        super(slotID, name, price);
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
