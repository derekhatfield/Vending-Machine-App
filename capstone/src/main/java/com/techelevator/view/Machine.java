package com.techelevator.view;

import com.techelevator.view.*;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public final class Machine {

    private BigDecimal currentBalance;
    private BigDecimal runningSalesTotal;
    private Map<String, Item> vendableItemMap;

    public static final BigDecimal DEFAULT_STARTING_BALANCE = new BigDecimal("0.00");
    private final BigDecimal QUARTER_VALUE = new BigDecimal("0.25");
    private final BigDecimal DIME_VALUE = new BigDecimal("0.10");
    private final BigDecimal NICKEL_VALUE = new BigDecimal("0.05");

    public Map<String, Item> getVendableItemMap() {
        return vendableItemMap;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public BigDecimal getRunningSalesTotal() {
        return runningSalesTotal;
    }

    public Machine() {
        this.currentBalance = new BigDecimal("0.0");
        this.runningSalesTotal = new BigDecimal("0.0");
        this.vendableItemMap = new TreeMap<>();
    }

    public void stock(Map<String, Item> vendableItemMap) {
        File stockFile = new File("C:\\Users\\Student\\workspace\\mod-1-capstone-pair-team-3\\capstone\\vendingmachine.csv");

        try (Scanner stockScanner = new Scanner(stockFile)) {
            while (stockScanner.hasNextLine()) {
                String[] stockInputArray = stockScanner.nextLine().split("\\|");
                if (stockInputArray[3].equals("Candy")) {
                    BigDecimal itemPrice = new BigDecimal(stockInputArray[2]);
                    vendableItemMap.put(stockInputArray[0], new Candy(stockInputArray[0], stockInputArray[1], itemPrice));

                }
                else if (stockInputArray[3].equals("Chip")) {
                    BigDecimal itemPrice = new BigDecimal(stockInputArray[2]);
                    vendableItemMap.put(stockInputArray[0], new Chip(stockInputArray[0], stockInputArray[1], itemPrice));

                }
                else if (stockInputArray[3].equals("Drink")) {
                    BigDecimal itemPrice = new BigDecimal(stockInputArray[2]);
                    vendableItemMap.put(stockInputArray[0], new Drink(stockInputArray[0], stockInputArray[1], itemPrice));

                }
                else if (stockInputArray[3].equals("Gum")) {
                    BigDecimal itemPrice = new BigDecimal(stockInputArray[2]);
                    vendableItemMap.put(stockInputArray[0], new Gum(stockInputArray[0], stockInputArray[1], itemPrice));
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Sorry, I could not find that file");
        }
    }

    public void printInventory() {
        System.out.println("");
        System.out.println("$*$*$*$*$*$*$*$*$*$*$*$*$*$*");
        System.out.println("   Our Current Selections:  ");
        System.out.println("$*$*$*$*$*$*$*$*$*$*$*$*$*$*");
        System.out.println("");
        for (Map.Entry<String, Item> item : vendableItemMap.entrySet()) {
            System.out.println(item.getValue().getName() + " | Stock: " + item.getValue().getStock());
        }
    }

    public void printPurchasableInventory() {
        System.out.println("");
        System.out.println("$*$*$*$*$*$*$*$*$*$*$*$*$*$*");
        System.out.println("            ~MENU~          ");
        System.out.println("$*$*$*$*$*$*$*$*$*$*$*$*$*$*");
        System.out.println("");
        for (Map.Entry<String, Item> item : vendableItemMap.entrySet()) {
            System.out.println(item.getValue().getSlotID() + " | " + item.getValue().getName() + " | " + item.getValue().getPrice() + " | " + item.getValue().getStock());
        }
        System.out.println("");
    }

    public void feedMoney (BigDecimal moneyToAdd) {
        this.currentBalance = this.currentBalance.add(moneyToAdd);
    }

    public void cashOut () {
        System.out.println("");
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
        System.out.println(numberOfQuarters + " Quarters, " + numberOfDimes + " Dimes, and " + numberOfNickels + " Nickels dispensed.");
    }

    public void dispenseItem (Item itemToDispense) {
        this.currentBalance = this.currentBalance.subtract(itemToDispense.getPrice());
        System.out.println("");
        System.out.println("Your item: " + itemToDispense.getName() + " | $" + itemToDispense.getPrice() + " | Balance remaining: $" + this.currentBalance);
        System.out.println("");
        System.out.println(itemToDispense.getMessage());
        this.runningSalesTotal = this.runningSalesTotal.add(itemToDispense.getPrice());
    }

    public void log(String description, BigDecimal firstNumber, BigDecimal secondNumber) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss a");
        LocalDateTime now = LocalDateTime.now();
        String logStamper = now.format(formatter);

        File logFile = new File("C:\\Users\\Student\\workspace\\mod-1-capstone-pair-team-3\\capstone\\Log.txt");
        try (PrintWriter logPrinter = new PrintWriter(new FileOutputStream(logFile, true))) {
            logPrinter.println(logStamper + " " + description + " $" + firstNumber + " $" + secondNumber);
        } catch (FileNotFoundException e) {
            System.out.println("I cannot find that file.");
        }
    }

    public void salesLog() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy_HH-mm-ss_a");
        LocalDateTime now = LocalDateTime.now();
        String logStamper = now.format(formatter);

        File salesLogDirectory = new File("C:\\Users\\Student\\workspace\\mod-1-capstone-pair-team-3\\capstone\\Sales_Logs");
        String salesLogFileName = logStamper + "_Report.txt";
        salesLogDirectory.mkdir();
        File salesLogFile = new File(salesLogDirectory, salesLogFileName);

        try {
            salesLogFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Sorry, that file couldn't be created.");
        }

        try (PrintWriter logPrinter = new PrintWriter(salesLogFile)) {
            for (Map.Entry<String, Item> itemEntry : vendableItemMap.entrySet()) {
                logPrinter.println(itemEntry.getValue().getName() + "|" + itemEntry.getValue().getRunningTotal());
            }
            logPrinter.println("");
            logPrinter.println("** TOTAL SALES ** $" + this.runningSalesTotal);
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, I cannot find that file.");
        }
    }
}
