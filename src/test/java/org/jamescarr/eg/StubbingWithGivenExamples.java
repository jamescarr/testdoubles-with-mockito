package org.jamescarr.eg;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.BDDMockito.*;

import org.junit.Before;
import org.junit.Test;


public class StubbingWithGivenExamples {
	private PricingService pricingService;
	
	@Before
	public void beforeEach(){
		pricingService = mock(PricingService.class);
	}
	@Test
	public void simpleExampleStubbedReturnValue(){
		given(pricingService.getPrice("SKU12345")).willReturn(25.22);
		
		assertThat(pricingService.getPrice("SKU12345"), equalTo(25.22));	
	}
	
	@Test
	public void stubbedReturnValueForConsecutiveCalls(){
		given(pricingService.getPrice("SKU12345")).willReturn(25.22, 33.22, 11.00);
		
		assertThat(pricingService.getPrice("SKU12345"), equalTo(25.22));
		assertThat(pricingService.getPrice("SKU12345"), equalTo(33.22));
		assertThat(pricingService.getPrice("SKU12345"), equalTo(11.00));
	}
	
	@Test
	public void returnDifferntValuesBasedOnParameters(){
		given(pricingService.getPrice("SKU12345")).willReturn(22.00);
		given(pricingService.getPrice("SKU54321")).willReturn(11.00);
		
		assertThat(pricingService.getPrice("SKU12345"), equalTo(22.00));
		assertThat(pricingService.getPrice("SKU54321"), equalTo(11.00));
		assertThat(pricingService.getPrice("SKU55555"), equalTo(0.00));
	}
	
	@Test
	public void shouldReturnSameValueForAnyString(){
		given(pricingService.getPrice(anyString())).willReturn(22.00);
		
		assertThat(pricingService.getPrice("SKU12345"), equalTo(22.00));
		assertThat(pricingService.getPrice("Blah Blah BLah"), equalTo(22.00));
	}
	
	
}