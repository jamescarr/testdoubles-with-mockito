package org.jamescarr.eg.mockito;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {

	private final Display display;
	private final Dispenser dispenser;
	
	private final Map<String, Item> items = new HashMap<String, Item>();
	private double amountEntered;
	
	public VendingMachine(Display display, Dispenser dispenser) {
		this.display = display;
		this.dispenser = dispenser;
	}

	public void stock(String keycode, String name, double price) {
		items.put(keycode, new Item(name, price));
	}

	public void enter(String keyCode) {
		if(items.containsKey("A1")){
			Item item = items.get(keyCode);
			if(amountEntered == items.get(keyCode).getPrice()){
				dispenser.dispense(item);
				display.displayMessage(item.getName() + " Dispensed");
				amountEntered = 0;
			}else{
				display.displayMessage(item.getPrice()-amountEntered+"");							
			}
		}else{
			display.displayMessage("OUT OF STOCK");
		}
	}

	public void insert(double amount) {
		this.amountEntered += amount;
	}


}
