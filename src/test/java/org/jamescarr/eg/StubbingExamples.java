package org.jamescarr.eg;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class StubbingExamples {
	private PricingService pricingService;
	
	@Before
	public void beforeEach(){
		pricingService = mock(PricingService.class);
	}
	@Test
	public void simpleExampleStubbedReturnValue(){
		when(pricingService.getPrice("SKU12345")).thenReturn(25.22);
		
		assertThat(pricingService.getPrice("SKU12345"), equalTo(25.22));	
	}
	
	@Test
	public void stubbedReturnValueForConsecutiveCalls(){
		when(pricingService.getPrice("SKU12345")).thenReturn(25.22, 33.22, 11.00);
		
		assertThat(pricingService.getPrice("SKU12345"), equalTo(25.22));
		assertThat(pricingService.getPrice("SKU12345"), equalTo(33.22));
		assertThat(pricingService.getPrice("SKU12345"), equalTo(11.00));
	}
	
	@Test
	public void returnDifferntValuesBasedOnParameters(){
		when(pricingService.getPrice("SKU12345")).thenReturn(22.00);
		when(pricingService.getPrice("SKU54321")).thenReturn(11.00);
		
		assertThat(pricingService.getPrice("SKU12345"), equalTo(22.00));
		assertThat(pricingService.getPrice("SKU54321"), equalTo(11.00));
		assertThat(pricingService.getPrice("SKU55555"), equalTo(0.00));
	}
	
	@Test
	public void shouldReturnSameValueForAnyString(){
		when(pricingService.getPrice(anyString())).thenReturn(22.00);
		
		assertThat(pricingService.getPrice("SKU12345"), equalTo(22.00));
		assertThat(pricingService.getPrice("Blah Blah BLah"), equalTo(22.00));
	}
	
	
}
