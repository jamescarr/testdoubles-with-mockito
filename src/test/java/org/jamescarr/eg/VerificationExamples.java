package org.jamescarr.eg;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;

import org.jamescarr.eg.values.Customer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;

public class VerificationExamples {
	private CustomerMapper customerMapper;
	private AuctionNotifier notifier;
	@Before
	public void beforeEach(){
		customerMapper = mock(CustomerMapper.class);
		notifier = mock(AuctionNotifier.class);
	}
	
	@Test
	public void verifyACustomerWasSaved(){
		customerMapper.save(new Customer());
		
		verify(customerMapper).save(any(Customer.class));
	}
	
	@Test
	public void verifyInvocationNeverHappened(){
		verify(customerMapper, never()).save(any(Customer.class));
	}
	
	@Test
	public void verifyInvocationsHappenedANumberOfTimes(){
		customerMapper.save(new Customer());
		customerMapper.save(new Customer());
		
		verify(customerMapper, times(2)).save(any(Customer.class));
	}
	@Test
	public void verifyingAnInvocationHappenedAtLeastANumberOfTimes(){
		customerMapper.save(new Customer());
		customerMapper.save(new Customer());
		
		verify(customerMapper, atLeast(2)).save(any(Customer.class));
	}
	@Test
	public void verificationsHaveNoOrderEnforced(){
		notifier.statusChanged(AuctionStatus.STARTED);
		notifier.statusChanged(AuctionStatus.IN_PROGRESS);
		notifier.statusChanged(AuctionStatus.WON);
		
		verify(notifier).statusChanged(AuctionStatus.WON);
		verify(notifier).statusChanged(AuctionStatus.STARTED);
		verify(notifier).statusChanged(AuctionStatus.IN_PROGRESS);
	}
	
	@Test
	public void verifyingInvocationsInOrder(){
		notifier.statusChanged(AuctionStatus.STARTED);
		notifier.statusChanged(AuctionStatus.IN_PROGRESS);
		notifier.statusChanged(AuctionStatus.WON);
		
		InOrder inOrder = inOrder(notifier);
		inOrder.verify(notifier).statusChanged(AuctionStatus.STARTED);
		inOrder.verify(notifier).statusChanged(AuctionStatus.IN_PROGRESS);
		inOrder.verify(notifier).statusChanged(AuctionStatus.WON);
	}
	@Test
	public void verifyInvocationWithObjectContainingSpecificValue(){
		customerMapper.save(new Customer("The Dude"));
		
		// this example requires hamcrest
		verify(customerMapper).save((Customer) argThat(hasProperty("name", equalTo("The Dude"))));
	}
	@Test
	public void usingAnArgumentCaptorForSpecificAssertions(){
		ArgumentCaptor<Customer> arg = ArgumentCaptor.forClass(Customer.class);

		customerMapper.save(new Customer("The Dude"));
		
		verify(customerMapper).save(arg.capture());
		assertThat(arg.getValue().getName(), equalTo("The Dude"));
	}
}
