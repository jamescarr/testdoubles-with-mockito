package org.jamescarr.eg.mockito;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {

	private final Display display;
	private final Map<String, Item> items = new HashMap<String, Item>();
	
	public VendingMachine(Display display) {
		this.display = display;
	}

	public void stock(String keycode, String name, double price) {
		items.put(keycode, new Item(name, price));
	}

	public void enter(String keyCode) {
		if(items.containsKey("A1")){
			display.displayMessage(items.get(keyCode).getPrice()+"");			
		}else{
			display.displayMessage("OUT OF STOCK");
		}
	}


}
