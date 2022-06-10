package com.techelevator.view;

import java.math.BigDecimal;

public class Gum extends Item {


    private String message = "CHEW CHEW, YUM!";

    public Gum(String slotID, String name, BigDecimal price) {
        super(slotID, name, price);
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
