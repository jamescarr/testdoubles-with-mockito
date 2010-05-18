package org.jamescarr.eg;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.jamescarr.eg.values.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ArgumentCaptorExamples {
	@Mock
	private CustomerMapper customerMapper;
	@Captor
	private ArgumentCaptor<Customer> arg;
	
	@Test
	public void shouldCaptureArgumentDuringVerificationPhase(){
		customerMapper.save(new Customer("Foo"));
		
		verify(customerMapper).save(arg.capture());
		assertThat(arg.getValue().getName(), equalTo("Foo"));
	}
	
	@Test
	public void shouldCaptureAsPartOfStubbingPhase(){
		willDoNothing().given(customerMapper).save(arg.capture());
		
		customerMapper.save(new Customer("Bar"));

		assertThat(arg.getValue().getName(), equalTo("Bar"));
	}
	
	@Test
	public void shouldCaptureLastValueWhenCalledMultipleTimes(){
		customerMapper.save(new Customer("Foo"));
		customerMapper.save(new Customer("Bar"));
		customerMapper.save(new Customer("Baz"));
		
		verify(customerMapper, times(3)).save(arg.capture());
		
		assertThat(arg.getValue().getName(), equalTo("Baz"));
	}
	
	@Test
	public void shouldAccessAllCapturedValuesWhenCalledMultipleTimes(){
		customerMapper.save(new Customer("Foo"));
		customerMapper.save(new Customer("Bar"));
		customerMapper.save(new Customer("Baz"));
		
		verify(customerMapper, times(3)).save(arg.capture());
		
		assertThat(arg.getAllValues().get(1).getName(), equalTo("Bar"));
	}
}
