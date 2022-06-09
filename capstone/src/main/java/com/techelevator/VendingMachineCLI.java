package com.techelevator;

import com.techelevator.view.Item;
import com.techelevator.view.Machine;
import com.techelevator.view.Menu;

import java.math.BigDecimal;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

	private Menu menu;

	static Machine vendOMatic9000 = new Machine();

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void runMain() {
		boolean mainLoop = true;
		while (mainLoop) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				vendOMatic9000.printInventory();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				runPurchase();
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println("");
				System.out.println("Thanks for using the Vend-O-Matic 9000!");
				System.exit(1);
			}
		}
	}

	public void runPurchase() {

		boolean purchaseLoop = true;
		Scanner userInput = new Scanner(System.in);
		while (purchaseLoop) {
			System.out.println("");
			System.out.println("Remaining Funds: $" + vendOMatic9000.getCurrentBalance());
			String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

			if (choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
				System.out.print("How much money would you like to add? >>> ");
				BigDecimal moneyToAdd = userInput.nextBigDecimal();
				vendOMatic9000.feedMoney(moneyToAdd);
				vendOMatic9000.log("FEED MONEY", moneyToAdd, vendOMatic9000.getCurrentBalance());
			} else if (choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {

				vendOMatic9000.printPurchasableInventory();
				System.out.print("Please select an item by slot number >>> ");
				String userSlotSelection = userInput.next().toUpperCase();
				Item itemToDispense = vendOMatic9000.getVendableItemMap().get(userSlotSelection);

					if (vendOMatic9000.getVendableItemMap().containsKey(userSlotSelection)) {
						if (vendOMatic9000.getCurrentBalance().compareTo(itemToDispense.getPrice()) == -1) {
							System.out.println("You do not have enough money!");
							continue;
						}
						if (itemToDispense.getStock() > 0) {
							vendOMatic9000.getVendableItemMap().get(userSlotSelection).vendItem(itemToDispense);
							vendOMatic9000.dispenseItem(itemToDispense);
							vendOMatic9000.log(itemToDispense.getName(), itemToDispense.getPrice(), vendOMatic9000.getCurrentBalance());
						} else {
							System.out.println("Sorry, that item is out of stock.");
						}
					} else {
						System.out.println("Sorry, we cannot find that item.");
					}

			} else if (choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
				vendOMatic9000.log("GIVE CHANGE", vendOMatic9000.getCurrentBalance(), vendOMatic9000.DEFAULT_STARTING_BALANCE);
				vendOMatic9000.cashOut();
				break;
			}
		}
	}



	public static void main(String[] args) {
		vendOMatic9000.stock(vendOMatic9000.getVendableItemMap());
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.runMain();
	}
}
