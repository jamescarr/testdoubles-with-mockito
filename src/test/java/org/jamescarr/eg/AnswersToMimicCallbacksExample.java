package org.jamescarr.eg;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.willAnswer;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


public class AnswersToMimicCallbacksExample {
	private UnprocessedOrders orders;
	
	@Before
	public void beforeEach(){
		orders = mock(UnprocessedOrders.class);
	}
	@Test
	public void invokeCallback(){
		willAnswer(withOrder("name", 20.00)).given(orders).handleUnprocessedOrders(any(OrderHandler.class));
		
		orders.handleUnprocessedOrders(new OrderHandler() {
			@Override
			public void process(Order order) {
				System.out.println(order.getName());
				System.out.println(order.getAmount());
			}
		});
	}

	private Answer<Void> withOrder(final String name, final double amount) {
		return new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				OrderHandler handler = (OrderHandler) args[0];
				handler.process(new Order(name, amount));
				return null;
			}
		};
	}
}
