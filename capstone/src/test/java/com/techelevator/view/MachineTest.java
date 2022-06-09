package com.techelevator.view;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class MachineTest {


    @Test
    public void machine_constructor_returns_with_matching_variables() {
        Machine testMachine = new Machine();
        BigDecimal testBD = new BigDecimal("0.0");
        Assert.assertEquals(testBD, testMachine.getCurrentBalance());
        Assert.assertEquals(0, testMachine.getVendableItemMap().size());
    }

    @Test
    public void feed_money_should_update_balance() {
        Machine testMachine = new Machine();
        BigDecimal testBD = new BigDecimal("5.00");
        BigDecimal testBd2 = new BigDecimal("1.65");
        BigDecimal resultBD = new BigDecimal("6.65");
        testMachine.feedMoney(testBD);
        testMachine.feedMoney(testBd2);
        Assert.assertEquals(resultBD, testMachine.getCurrentBalance());
    }
}
