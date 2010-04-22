package org.jamescarr.eg.mockito;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class VendingMachineTest {
	private VendingMachine vendingMachine;
	private Display display;
	
	@Before
	public void beforeEach(){
		display = mock(Display.class);
		vendingMachine = new VendingMachine(display);
	}
	
	@Test
	public void shouldDisplayItemPriceWhenKeyedIn(){
		vendingMachine.stock("A1", "Pepsi", 1.25);

		vendingMachine.enter("A1");

		verify(display).displayMessage("1.25");
	}
	/**
	 * given an item is not in stock
	 * when key is entered
	 * then display not in stock
	 */
	@Test
	public void shouldDisplayItemOutOfStockWhenEnteredItemIsNotInStock(){
		vendingMachine.enter("A1");

		verify(display).displayMessage("OUT OF STOCK");
	}	
	/**
	 * given an item is in stock
	 * and the amount required has been inserted
	 * when the key is entered
	 * then dispense beverage
	 */
	
	/**
	 * given an item is in stock
	 * and the amount required has been inserted
	 * when the key is entered
	 * and the beverage has been dispensed
	 * then display "Item Name Dispensed"
	 */
}
