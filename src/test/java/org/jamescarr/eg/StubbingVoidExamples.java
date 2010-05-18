package org.jamescarr.eg;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.junit.matchers.JUnitMatchers.containsString;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import org.jamescarr.eg.values.Customer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.exceptions.base.MockitoException;

public class StubbingVoidExamples {
	private CustomerMapper mapper;

	@Before
	public void beforeEach() {
		mapper = (CustomerMapper) mock(CustomerMapper.class);
	}

	@Test(expected = RuntimeException.class)
	public void makeSaveThrowAnException() {
		doThrow(new RuntimeException()).when(mapper).save(any(Customer.class));

		mapper.save(new Customer());
	}

	@Test(expected = RuntimeException.class)
	public void throwAnExceptionUsingGivenTerminology() {
		willThrow(new RuntimeException()).given(mapper).save(
				any(Customer.class));

		mapper.save(new Customer());
	}

	@Test
	public void cannotThrowACheckedExceptionIfNotDefinedOnMethodSignature() {
		try {
			willThrow(new Exception()).given(mapper).save(any(Customer.class));
			fail("should throw a MockitoException");
		} catch (MockitoException e) {
			assertThat(e.getMessage(), containsString("Checked exception is invalid for this method!"));
		} catch (Exception e) {
			fail("Won't happen.");
		}
	}
	
}
