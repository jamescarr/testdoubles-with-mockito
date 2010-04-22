package org.jamescarr.eg.mockito;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
public class VendingMachineTest {
	private VendingMachine vendingMachine;
	private Display display;
	private Dispenser dispenser;
	private final ArgumentCaptor<Item> arg = ArgumentCaptor.forClass(Item.class);
	
	@Before
	public void beforeEach(){
		display = mock(Display.class);
		dispenser = mock(Dispenser.class);
		vendingMachine = new VendingMachine(display, dispenser);
	}
	
	@Test
	public void shouldDisplayItemPriceWhenKeyedIn(){
		vendingMachine.stock("A1", "Pepsi", 1.25);

		vendingMachine.enter("A1");

		verify(display).displayMessage("1.25");
	}

	@Test
	public void shouldDisplayRemaingAmountNeededWhenKeyedInWithMoneyEntered(){
		vendingMachine.stock("A1", "Pepsi", 1.25);

		vendingMachine.insert(.10);
		vendingMachine.enter("A1");

		verify(display).displayMessage("1.15");
	}

	
	@Test
	public void shouldDisplayItemOutOfStockWhenEnteredItemIsNotInStock(){
		vendingMachine.enter("A1");

		verify(display).displayMessage("OUT OF STOCK");
	}
	
	@Test
	public void shouldDispenseBeverageWhenFullAmountInserted(){
		vendingMachine.stock("A1", "Pepsi", 1.25);
		
		vendingMachine.insert(1.00);
		vendingMachine.insert(0.25);
		vendingMachine.enter("A1");

		verify(dispenser).dispense(arg.capture());
		
		assertThat(arg.getValue().getName(), equalTo("Pepsi"));
	}
	
	@Test
	public void shouldDisplayItemNameDispensedWhenItIsDispensed(){
		vendingMachine.stock("A1", "Pepsi", 1.25);
		
		vendingMachine.insert(1.00);
		vendingMachine.insert(0.25);
		vendingMachine.enter("A1");

		verify(display).displayMessage("Pepsi Dispensed");		
	}
	@Test
	public void shouldDisplayItemPriceAgainIfEnteredAfterDispertion(){
		vendingMachine.stock("A1", "Pepsi", 1.25);
		
		vendingMachine.insert(1.00);
		vendingMachine.insert(0.25);
		vendingMachine.enter("A1");
		vendingMachine.enter("A1");

		verify(display).displayMessage("1.25");
	}
}
