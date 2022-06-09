package com.techelevator;

import com.techelevator.view.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Machine {

    private double currentBalance;
    private double runningSalesTotal;
    private List<Item> vendableItemList;

    private final double QUARTER_VALUE = 0.25;
    private final double DIME_VALUE = 0.10;
    private final double NICKEL_VALUE = 0.05;

    public Machine() {
        this.currentBalance = 0.0;
        this.runningSalesTotal = 0.0;
        this.vendableItemList = new ArrayList<>();
    }

    public void stock(List<Item> vendableItemList) {
        File stockFile = new File("C:\\Users\\Student\\workspace\\mod-1-capstone-pair-team-3\\capstone\\vendingmachine.csv");
        try (Scanner stockScanner = new Scanner(stockFile)) {
            while (stockScanner.hasNextLine()) {
                String[] stockInputArray = stockScanner.nextLine().split("\\|");
                if (stockInputArray[3].equals("Candy")) {
                    double itemPrice = Double.parseDouble(stockInputArray[2]);
                    vendableItemList.add(new Candy(stockInputArray[0], stockInputArray[1], itemPrice, "MUNCH MUNCH, YUM!"));

                }
                else if (stockInputArray[3].equals("Chip")) {
                    double itemPrice = Double.parseDouble(stockInputArray[2]);
                    vendableItemList.add(new Chip(stockInputArray[0], stockInputArray[1], itemPrice, "CRUNCH CRUNCH, YUM!"));
                }
                else if (stockInputArray[3].equals("Drink")) {
                    double itemPrice = Double.parseDouble(stockInputArray[2]);
                    vendableItemList.add(new Drink(stockInputArray[0], stockInputArray[1], itemPrice, "GLUG GLUG, YUM!"));
                }
                else if (stockInputArray[3].equals("Gum")) {
                    double itemPrice = Double.parseDouble(stockInputArray[2]);
                    vendableItemList.add(new Gum(stockInputArray[0], stockInputArray[1], itemPrice, "CHEW CHEW, YUM!"));
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Sorry, I could not find that file");
        }
    }

    public List<Item> getVendableItemList() {
        return this.vendableItemList;
    }
}
