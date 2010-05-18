package org.jamescarr.eg;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class SpyingRealObjectExample {
	private List<String> list;
	
	@Before
	public void beforeEach(){
		list = spy(new ArrayList<String>());
	}
	
	@Test
	public void stubbingMethodNormallyWontWork(){
		given(list.get(22)).willReturn("Hello");
	}
	
	@Test
	public void stubUsingAlternativeForm(){
		willReturn("Hello").given(list).get(22);
		
		assertThat(list.get(22), equalTo("Hello"));
	}
	
	@Test
	public void verifyingMessagesPassedToRealObjects(){
		list.add("hello");
		
		verify(list).add("hello");
		
		assertThat(list.get(0), equalTo("hello"));
	}
}
