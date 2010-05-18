package org.jamescarr.eg;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class OrderCallbackAnswer implements Answer<Void> {
	private final Order orderToPassToCallback;
	
	private OrderCallbackAnswer(String name, Double amount) {
		this.orderToPassToCallback = new Order(name, amount);
	}

	public static Answer<Void> withOrder(final String name, final double amount) {
		return new OrderCallbackAnswer(name, amount);
	}

	@Override
	public Void answer(InvocationOnMock invocation) throws Throwable {
		OrderHandler callback = (OrderHandler)invocation.getArguments()[0];
		callback.process(orderToPassToCallback);
		return null;
	}
}
