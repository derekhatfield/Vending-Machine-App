package com.techelevator;

import com.techelevator.view.Candy;
import com.techelevator.view.Menu;
import com.techelevator.view.Vendable;

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

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}




	public void runMain() {
		boolean mainLoop = true;
		while (mainLoop) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				runPurchase();
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				//do exit
			}
		}
	}

	public void runPurchase() {
		boolean purchaseLoop = true;
		while (purchaseLoop) {
			String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

			if (choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
				// do feed money
			} else if (choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
				//
			} else if (choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
				//do finish
			}
		}
	}

	public static void main(String[] args) {
		Machine vendOMatic9000 = new Machine();
		vendOMatic9000.stock(vendOMatic9000.getVendableItemList());



		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.runMain();


	}
}
