package com.techelevator.view;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class MachineTest {

    Machine testMachine = new Machine();



    @Test
    public void machine_constructor_returns_with_matching_variables() {
        BigDecimal testBD = new BigDecimal("0.0");
        Assert.assertEquals(testBD, testMachine.getCurrentBalance());
        Assert.assertEquals(0, testMachine.getVendableItemMap().size());
    }

    @Test
    public void feed_money_should_update_balance() {
        BigDecimal testBD = new BigDecimal("5.00");
        BigDecimal testBd2 = new BigDecimal("1.65");
        BigDecimal resultBD = new BigDecimal("6.65");
        testMachine.feedMoney(testBD);
        testMachine.feedMoney(testBd2);
        Assert.assertEquals(resultBD, testMachine.getCurrentBalance());
    }

    @Test
    public void dispense_item_should_return_correct_message_and_price() {
         BigDecimal testBD = new BigDecimal("3.99");
         BigDecimal testBD2 = new BigDecimal("5.00");
         Candy testCandy = new Candy("Z5", "KitKat", testBD);
         Chip testChip = new Chip("X7", "VooDoo", testBD);
         Drink testDrink = new Drink("W4", "Gatorade", testBD2);
         Gum testGum = new Gum("P2", "Big League Chew", testBD);
         testMachine.getVendableItemMap().put("Z5", testCandy);
         testMachine.dispenseItem(testCandy);
         testMachine.feedMoney(testBD);
         Assert.assertEquals(testBD, testMachine.getRunningSalesTotal());
         Assert.assertEquals("MUNCH MUNCH, YUM!", testCandy.getMessage());
         Assert.assertEquals("CRUNCH CRUNCH, YUM!", testChip.getMessage());
         Assert.assertEquals("GLUG GLUG, YUM!", testDrink.getMessage());
         Assert.assertEquals("CHEW CHEW, YUM!", testGum.getMessage());

    }
}
