package org.jamescarr.eg;

import static org.jamescarr.eg.OrderCallbackAnswer.withOrder;
import static org.mockito.BDDMockito.willAnswer;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
public class CallbackCleanerExample {
	private UnprocessedOrders orders;

	@Before
	public void beforeEach() {
		orders = mock(UnprocessedOrders.class);
	}

	@Test
	public void invokeCallback() {
		willAnswer(withOrder("cookies", 20.00)).given(orders)
				.handleUnprocessedOrders(any(OrderHandler.class));

		orders.handleUnprocessedOrders(new OrderHandler() {
			@Override
			public void process(Order order) {
				System.out.println(order.getName());
				System.out.println(order.getAmount());
			}
		});
	}

}
