package org.jamescarr.eg;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.willAnswer;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import org.jamescarr.eg.values.Customer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


// This is bad, but sometimes necessary for legacy code
public class AnswerForSpecificBehaviorExample {
	private Marshaller<Customer> marshaller;
	
	@Before
	public void beforeEach(){
		marshaller = mock(Marshaller.class);
	}
	
	@Test
	public void shouldWriteSomethingToOutputStream(){
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		willAnswer(writeCustomerNameToOutput()).given(marshaller).marshall(any(Customer.class), eq(out));
		
		Customer c = new Customer();
		c.setName("James");
		marshaller.marshall(c, out);
		
		assertThat(out.toString(), equalTo("James"));
	}

	
	
	
	
	
	
	
	
	
	private Answer<Void> writeCustomerNameToOutput() {
		return new Answer<Void>(){
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Customer customer = (Customer) invocation.getArguments()[0];
				OutputStream out = (OutputStream) invocation.getArguments()[1];
				out.write(customer.getName().getBytes());
				return null;
			}
		};
	}
}
