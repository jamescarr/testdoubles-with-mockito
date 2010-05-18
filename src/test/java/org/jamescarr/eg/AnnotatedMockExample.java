package org.jamescarr.eg;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class AnnotatedMockExample {
	@Mock 
	private PricingService pricingService;
	
	@Test
	public void simpleExampleStubbedReturnValue(){
		when(pricingService.getPrice("SKU12345")).thenReturn(25.22);
		
		assertThat(pricingService.getPrice("SKU12345"), equalTo(25.22));	
	}
}
