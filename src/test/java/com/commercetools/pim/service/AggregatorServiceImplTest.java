package com.commercetools.pim.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.commercetools.pim.AggregatorCommonSetUpClass;
import com.commercetools.pim.dao.model.AggregatorProductDaoObject;
import com.commercetools.pim.exception.AggregatorException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AggregatorServiceImplTest extends AggregatorCommonSetUpClass{
	
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void testDumpDataPositiveData() throws AggregatorException {
		List<AggregatorProductDaoObject> aggregatorProductDaoObjectList = new ArrayList<>();
		
		AggregatorProductDaoObject aggregatorProductDaoObject = new AggregatorProductDaoObject();
		aggregatorProductDaoObject.setAvailable(false);
		aggregatorProductDaoObject.setName("Name1");
		aggregatorProductDaoObject.setProvider("provider1");
		aggregatorProductDaoObject.setDescription("Description1");
		aggregatorProductDaoObject.setMeasurementUnit("MeasurementUnit1");
		aggregatorProductDaoObject.setRequestTimeStamp(1567242403906L);
		aggregatorProductDaoObject.setId("UUID1");
		aggregatorProductDaoObject.setNumberOfTimesUpdated(1L);
		
		aggregatorProductDaoObjectList.add(aggregatorProductDaoObject);
		
		when(aggregatorProductDaoObjectRepository.findRecord(anyString(),anyString())).thenReturn(aggregatorProductDaoObjectList);
		
		doAnswer(new Answer<Void>() {
			 public Void answer(InvocationOnMock invocation) {
				 Object[] args = invocation.getArguments();
				 AggregatorProductDaoObject aggregatorProductDaoObject = (AggregatorProductDaoObject) args[0];
				 assertEquals("Name1" , aggregatorProductDaoObject.getName());
				 assertEquals(Long.valueOf(1567242403907L) , aggregatorProductDaoObject.getRequestTimeStamp());
				 assertEquals("provider1" , aggregatorProductDaoObject.getProvider());
				 assertTrue(aggregatorProductDaoObject.getAvailable());
				 assertEquals("Description2" , aggregatorProductDaoObject.getDescription());
				 assertEquals("UUID1", aggregatorProductDaoObject.getId());
				 assertEquals("MeasurementUnit2" , aggregatorProductDaoObject.getMeasurementUnit());
				 return null;
			 }
		}).when(aggregatorProductDaoObjectRepository).save(any(AggregatorProductDaoObject.class));
		
		aggregatorServiceImpl.dumpDataInBackend("{"
				+ "\"provider\":\"provider1\","
				+ "\"requestTimeStampInMS\":1567242403907,"
				+ "\"name\":\"Name1\","
				+ "\"available\":true,"
				+ "\"description\":\"Description2\","
				+ "\"uuid\":\"UUID2\","
				+ "\"measurementUnit\":\"MeasurementUnit2\"}" );
	}
	
	@Test(expected=AggregatorException.class)
	public void testDumpDataEmptyRequestBody() throws AggregatorException {
		aggregatorServiceImpl.dumpDataInBackend("" );
	}
	
	@Test(expected=AggregatorException.class)
	public void testDumpDataImproperRequestBody() throws AggregatorException {
		aggregatorServiceImpl.dumpDataInBackend("\"abc\":\"def\"");
	}
	
}
