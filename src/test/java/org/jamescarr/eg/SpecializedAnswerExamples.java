package org.jamescarr.eg;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

import org.jamescarr.eg.values.Customer;
import org.jamescarr.eg.values.Street;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.exceptions.verification.SmartNullPointerException;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SpecializedAnswerExamples {
	@Mock
	private Customer normalStub;
	@Mock(answer=Answers.RETURNS_SMART_NULLS)
	private Customer stubWithSmartNulls;
	@Mock(answer=Answers.RETURNS_DEEP_STUBS)
	private Customer stubWithDeepStubbing;
	@Mock(answer=Answers.RETURNS_MOCKS)
	private Customer stubThatReturnsMocks;
	
	@Test
	public void shouldReturnNullForUnstubbedMethods(){
		assertNull(normalStub.getAddress());
	}
	
	@Test
	public void shouldReturnNonNullValues(){
		assertNotNull(stubWithSmartNulls.getAddress());
	}
	
	@Test(expected=SmartNullPointerException.class)
	public void cannotTreatSmartNullObjectsAsATestDouble(){
		given(stubWithSmartNulls.getAddress().getCity()).willReturn("Hannibal");
	}
	
	@Test
	public void stubbingATrainWreck(){
		given(stubWithDeepStubbing.getAddress().getStreet().getHouseNumber().getNumber()).willReturn(2600);
		
		assertThat(stubWithDeepStubbing.getAddress().getStreet().getHouseNumber().getNumber(), equalTo(2600));
	}
	
	@Test
	public void returnsMocksCreatesDefaultValuesWhenPossible(){
		assertThat(stubThatReturnsMocks.getAddress().getState(), equalTo(""));		
	}
	@Test
	public void returnsUniqueTestDoubleForEachCallWithReturnsMocks(){
		
		Street firstTime = stubThatReturnsMocks.getAddress().getStreet();
		Street secondTime =stubThatReturnsMocks.getAddress().getStreet();
		
		assertThat(firstTime, is(not(secondTime)));
	}
	
	@Test
	public void returnsSameTestDoubleForEachCallWithReturnsDeepStubs(){
		
		Street firstTime = stubWithDeepStubbing.getAddress().getStreet();
		Street secondTime =stubWithDeepStubbing.getAddress().getStreet();
		
		assertThat(firstTime, is(secondTime));
	}
}
