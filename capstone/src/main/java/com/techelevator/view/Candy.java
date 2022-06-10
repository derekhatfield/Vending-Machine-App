package com.techelevator.view;

import java.math.BigDecimal;

public final class Candy extends Item {

    private String message = "MUNCH MUNCH, YUM!";

    public Candy(String slotID, String name, BigDecimal price) {
        super(slotID, name, price);
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
