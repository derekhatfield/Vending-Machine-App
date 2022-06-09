package com.techelevator;

import com.techelevator.view.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

public class Machine {

    private BigDecimal currentBalance;
    private BigDecimal runningSalesTotal;
    private Map<String, Item> vendableItemMap;

    private final BigDecimal QUARTER_VALUE = new BigDecimal("0.25");
    private final BigDecimal DIME_VALUE = new BigDecimal("0.10");
    private final BigDecimal NICKEL_VALUE = new BigDecimal("0.05");

    public Machine() {
        this.currentBalance = new BigDecimal("0.0");
        this.runningSalesTotal = new BigDecimal("0.0");
        this.vendableItemMap = new HashMap<>();
    }

    public void stock(Map<String, Item> vendableItemMap) {
        File stockFile = new File("C:\\Users\\Student\\workspace\\mod-1-capstone-pair-team-3\\capstone\\vendingmachine.csv");
        try (Scanner stockScanner = new Scanner(stockFile)) {
            while (stockScanner.hasNextLine()) {
                String[] stockInputArray = stockScanner.nextLine().split("\\|");
                if (stockInputArray[3].equals("Candy")) {
                    //double itemPriceDouble = Double.parseDouble((stockInputArray[2]));
                    BigDecimal itemPrice = new BigDecimal(stockInputArray[2]);
                    vendableItemMap.put(stockInputArray[0], new Candy(stockInputArray[0], stockInputArray[1], itemPrice, "MUNCH MUNCH, YUM!"));

                }
                else if (stockInputArray[3].equals("Chip")) {
                    //double itemPriceDouble = Double.parseDouble((stockInputArray[2]));
                    BigDecimal itemPrice = new BigDecimal(stockInputArray[2]);
                    vendableItemMap.put(stockInputArray[0], new Chip(stockInputArray[0], stockInputArray[1], itemPrice, "CRUNCH CRUNCH, YUM!"));
                }
                else if (stockInputArray[3].equals("Drink")) {
                    //double itemPriceDouble = Double.parseDouble((stockInputArray[2]));
                    BigDecimal itemPrice = new BigDecimal(stockInputArray[2]);
                    vendableItemMap.put(stockInputArray[0], new Drink(stockInputArray[0], stockInputArray[1], itemPrice, "GLUG GLUG, YUM!"));
                }
                else if (stockInputArray[3].equals("Gum")) {
                    //double itemPriceDouble = Double.parseDouble((stockInputArray[2]));
                    BigDecimal itemPrice = new BigDecimal(stockInputArray[2]);
                    vendableItemMap.put(stockInputArray[0], new Gum(stockInputArray[0], stockInputArray[1], itemPrice, "CHEW CHEW, YUM!"));
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Sorry, I could not find that file");
        }
    }

    public void printInventory() {
        for (Map.Entry<String, Item> item : vendableItemMap.entrySet()) {
            System.out.println(item.getValue().getName() + " " + item.getValue().getStock());
        }
    }

    public void printPurchasableInventory() {
        for (Map.Entry<String, Item> item : vendableItemMap.entrySet()) {
            System.out.println(item.getValue().getSlotID() + " | " + item.getValue().getName() + " | " + item.getValue().getPrice() + " | " + item.getValue().getStock());
        }
    }

    public void feedMoney (BigDecimal moneyToAdd) {
        this.currentBalance = this.currentBalance.add(moneyToAdd);
    }

    public void cashOut () {
        System.out.println("Your change is: $" + this.currentBalance);
        int numberOfQuarters = 0;
        int numberOfDimes = 0;
        int numberOfNickels = 0;

        while (currentBalance.compareTo(QUARTER_VALUE) == 1 || currentBalance.compareTo(QUARTER_VALUE) == 0) {
            numberOfQuarters++;
            this.currentBalance = this.currentBalance.subtract(QUARTER_VALUE);
        }
        while (currentBalance.compareTo(DIME_VALUE) == 1 || currentBalance.compareTo(DIME_VALUE) == 0) {
            numberOfDimes++;
            this.currentBalance = this.currentBalance.subtract(DIME_VALUE);
        }
        while (currentBalance.compareTo(NICKEL_VALUE) == 1 || currentBalance.compareTo(NICKEL_VALUE) == 0) {
            numberOfNickels++;
            this.currentBalance = this.currentBalance.subtract(NICKEL_VALUE);
        }
        System.out.println(numberOfQuarters + " Quarters " + numberOfDimes + " Dimes " + numberOfNickels + " Nickels.");
    }

    public void dispenseItem (Item itemToDispense) {
        this.currentBalance = this.currentBalance.subtract(itemToDispense.getPrice());
        System.out.println("Your item: " + itemToDispense.getName() + " | $" + itemToDispense.getPrice() + " | Balance remaining: $" + this.currentBalance);
        System.out.println(itemToDispense.getMessage());
    }

    public Map<String, Item> getVendableItemMap() {
        return vendableItemMap;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }
}
