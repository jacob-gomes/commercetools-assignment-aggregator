package com.commercetools.pim.dao.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
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
public class AggregatorProductDaoServiceImplTest extends AggregatorCommonSetUpClass{

	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void testDumpDataForCreation() throws AggregatorException {
		AggregatorProductDaoObject aggregatorProductDaoObject = new AggregatorProductDaoObject();
		aggregatorProductDaoObject.setName("Name1");
		aggregatorProductDaoObject.setRequestTimeStamp(Long.valueOf(1567242403906L));
		aggregatorProductDaoObject.setProvider("provider1");
		aggregatorProductDaoObject.setAvailable(true);
		aggregatorProductDaoObject.setDescription("Description1");
		aggregatorProductDaoObject.setId("UUID1");
		aggregatorProductDaoObject.setMeasurementUnit("MeasurementUnit1");
		
		when(aggregatorProductDaoObjectRepository.findRecord(anyString(),anyString())).thenReturn(new ArrayList<AggregatorProductDaoObject>());
		doAnswer(new Answer<Void>() {
			 public Void answer(InvocationOnMock invocation) {
				 Object[] args = invocation.getArguments();
				 AggregatorProductDaoObject aggregatorProductDaoObject = (AggregatorProductDaoObject) args[0];
				 assertEquals("Name1", aggregatorProductDaoObject.getName());
				 assertEquals(Long.valueOf(1567242403906L) , aggregatorProductDaoObject.getRequestTimeStamp());
				 assertEquals("provider1", aggregatorProductDaoObject.getProvider());
				 assertTrue(aggregatorProductDaoObject.getAvailable());
				 assertEquals("Description1", aggregatorProductDaoObject.getDescription());
				 assertEquals("UUID1", aggregatorProductDaoObject.getId());
				 assertEquals("MeasurementUnit1", aggregatorProductDaoObject.getMeasurementUnit());
				 assertTrue(null == aggregatorProductDaoObject.getNumberOfTimesUpdated());
				 return null;
			 }
		}).when(aggregatorProductDaoObjectRepository).save(any(AggregatorProductDaoObject.class));
		
		aggregatorProductDaoServiceImpl.checkExistenceAndDumpData(aggregatorProductDaoObject);
	}
	
	@Test
	public void testDumpDataForUpdation() throws AggregatorException {
		List<AggregatorProductDaoObject> aggregatorProductDaoObjectList = new ArrayList<>();
		
		AggregatorProductDaoObject aggregatorProductDaoObject = new AggregatorProductDaoObject();
		aggregatorProductDaoObject.setName("Name1");
		aggregatorProductDaoObject.setRequestTimeStamp(Long.valueOf(1567242403906L));
		aggregatorProductDaoObject.setProvider("provider1");
		aggregatorProductDaoObject.setAvailable(true);
		aggregatorProductDaoObject.setDescription("Description1");
		aggregatorProductDaoObject.setId("UUID1");
		aggregatorProductDaoObject.setMeasurementUnit("MeasurementUnit1");
		aggregatorProductDaoObject.setNumberOfTimesUpdated(0L);
		
		aggregatorProductDaoObjectList.add(aggregatorProductDaoObject);
		
		when(aggregatorProductDaoObjectRepository.findRecord(anyString(),anyString())).thenReturn(aggregatorProductDaoObjectList);
		doAnswer(new Answer<Void>() {
			 public Void answer(InvocationOnMock invocation) {
				 Object[] args = invocation.getArguments();
				 AggregatorProductDaoObject aggregatorProductDaoObject = (AggregatorProductDaoObject) args[0];
				 assertEquals("Name1", aggregatorProductDaoObject.getName());
				 assertEquals(Long.valueOf(1567242403907L) , aggregatorProductDaoObject.getRequestTimeStamp());
				 assertEquals( "provider1", aggregatorProductDaoObject.getProvider());
				 assertFalse(aggregatorProductDaoObject.getAvailable());
				 assertEquals("Description2",aggregatorProductDaoObject.getDescription());
				 assertEquals("UUID1",aggregatorProductDaoObject.getId());
				 assertEquals("MeasurementUnit2",aggregatorProductDaoObject.getMeasurementUnit());
				 assertEquals(Long.valueOf(1L), aggregatorProductDaoObject.getNumberOfTimesUpdated());
				 assertTrue(null != aggregatorProductDaoObject.getUpdatedDateTime());
				 return null;
			 }
		}).when(aggregatorProductDaoObjectRepository).save(any(AggregatorProductDaoObject.class));
		
		AggregatorProductDaoObject aggregatorProductDaoObject2 = new AggregatorProductDaoObject();
		aggregatorProductDaoObject2.setName("Name1");
		aggregatorProductDaoObject2.setRequestTimeStamp(Long.valueOf(1567242403907L));
		aggregatorProductDaoObject2.setProvider("provider1");
		aggregatorProductDaoObject2.setAvailable(false);
		aggregatorProductDaoObject2.setDescription("Description2");
		aggregatorProductDaoObject2.setId("UUID2");
		aggregatorProductDaoObject2.setMeasurementUnit("MeasurementUnit2");
		aggregatorProductDaoServiceImpl.checkExistenceAndDumpData(aggregatorProductDaoObject2);
	}
	
	@Test
	public void testDumpDataForUpdationStaleDataNonEqual() throws AggregatorException {
		List<AggregatorProductDaoObject> aggregatorProductDaoObjectList = new ArrayList<>();
		
		AggregatorProductDaoObject aggregatorProductDaoObject = new AggregatorProductDaoObject();
		aggregatorProductDaoObject.setName("Name1");
		aggregatorProductDaoObject.setRequestTimeStamp(Long.valueOf(1567242403906L));
		aggregatorProductDaoObject.setProvider("provider1");
		aggregatorProductDaoObject.setAvailable(true);
		aggregatorProductDaoObject.setDescription("Description1");
		aggregatorProductDaoObject.setId("UUID1");
		aggregatorProductDaoObject.setMeasurementUnit("MeasurementUnit1");
		aggregatorProductDaoObject.setNumberOfTimesUpdated(0L);
		aggregatorProductDaoObject.setRequestTimeStamp(1567242403906L);		
		aggregatorProductDaoObjectList.add(aggregatorProductDaoObject);
		
		when(aggregatorProductDaoObjectRepository.findRecord(anyString(),anyString())).thenReturn(aggregatorProductDaoObjectList);
		doAnswer(new Answer<Void>() {
			 public Void answer(InvocationOnMock invocation) {
				 Object[] args = invocation.getArguments();
				 AggregatorProductDaoObject aggregatorProductDaoObject = (AggregatorProductDaoObject) args[0];
				 assertEquals("Name1", aggregatorProductDaoObject.getName());
				 assertEquals(Long.valueOf(1567242403906L) , aggregatorProductDaoObject.getRequestTimeStamp());
				 assertEquals( "provider1", aggregatorProductDaoObject.getProvider());
				 assertTrue(aggregatorProductDaoObject.getAvailable());
				 assertEquals("Description1",aggregatorProductDaoObject.getDescription());
				 assertEquals("UUID1",aggregatorProductDaoObject.getId());
				 assertEquals("MeasurementUnit1",aggregatorProductDaoObject.getMeasurementUnit());
				 assertEquals(Long.valueOf(1L), aggregatorProductDaoObject.getNumberOfTimesUpdated());
				 assertTrue(null != aggregatorProductDaoObject.getUpdatedDateTime());
				 return null;
			 }
		}).when(aggregatorProductDaoObjectRepository).save(any(AggregatorProductDaoObject.class));
		
		AggregatorProductDaoObject aggregatorProductDaoObject2 = new AggregatorProductDaoObject();
		aggregatorProductDaoObject2.setName("Name1");
		aggregatorProductDaoObject2.setRequestTimeStamp(Long.valueOf(1567242403907L));
		aggregatorProductDaoObject2.setProvider("provider1");
		aggregatorProductDaoObject2.setAvailable(false);
		aggregatorProductDaoObject2.setDescription("Description2");
		aggregatorProductDaoObject2.setId("UUID2");
		aggregatorProductDaoObject2.setMeasurementUnit("MeasurementUnit2");
		aggregatorProductDaoObject2.setRequestTimeStamp(1567242403905L);
		aggregatorProductDaoServiceImpl.checkExistenceAndDumpData(aggregatorProductDaoObject2);
	}
	
	@Test
	public void testDumpDataForUpdationStaleDataEqual() throws AggregatorException {
		List<AggregatorProductDaoObject> aggregatorProductDaoObjectList = new ArrayList<>();
		
		AggregatorProductDaoObject aggregatorProductDaoObject = new AggregatorProductDaoObject();
		aggregatorProductDaoObject.setName("Name1");
		aggregatorProductDaoObject.setRequestTimeStamp(Long.valueOf(1567242403906L));
		aggregatorProductDaoObject.setProvider("provider1");
		aggregatorProductDaoObject.setAvailable(true);
		aggregatorProductDaoObject.setDescription("Description1");
		aggregatorProductDaoObject.setId("UUID1");
		aggregatorProductDaoObject.setMeasurementUnit("MeasurementUnit1");
		aggregatorProductDaoObject.setNumberOfTimesUpdated(0L);
		aggregatorProductDaoObject.setRequestTimeStamp(1567242403906L);		
		aggregatorProductDaoObjectList.add(aggregatorProductDaoObject);
		
		when(aggregatorProductDaoObjectRepository.findRecord(anyString(),anyString())).thenReturn(aggregatorProductDaoObjectList);
		doAnswer(new Answer<Void>() {
			 public Void answer(InvocationOnMock invocation) {
				 Object[] args = invocation.getArguments();
				 AggregatorProductDaoObject aggregatorProductDaoObject = (AggregatorProductDaoObject) args[0];
				 assertEquals("Name1", aggregatorProductDaoObject.getName());
				 assertEquals(Long.valueOf(1567242403906L) , aggregatorProductDaoObject.getRequestTimeStamp());
				 assertEquals( "provider1", aggregatorProductDaoObject.getProvider());
				 assertTrue(aggregatorProductDaoObject.getAvailable());
				 assertEquals("Description1",aggregatorProductDaoObject.getDescription());
				 assertEquals("UUID1",aggregatorProductDaoObject.getId());
				 assertEquals("MeasurementUnit1",aggregatorProductDaoObject.getMeasurementUnit());
				 assertEquals(Long.valueOf(0L), aggregatorProductDaoObject.getNumberOfTimesUpdated());
				 assertTrue(null == aggregatorProductDaoObject.getUpdatedDateTime());
				 return null;
			 }
		}).when(aggregatorProductDaoObjectRepository).save(any(AggregatorProductDaoObject.class));
		
		AggregatorProductDaoObject aggregatorProductDaoObject2 = new AggregatorProductDaoObject();
		aggregatorProductDaoObject2.setName("Name1");
		aggregatorProductDaoObject2.setRequestTimeStamp(Long.valueOf(1567242403907L));
		aggregatorProductDaoObject2.setProvider("provider1");
		aggregatorProductDaoObject2.setAvailable(true);
		aggregatorProductDaoObject2.setDescription("Description1");
		aggregatorProductDaoObject2.setId("UUID1");
		aggregatorProductDaoObject2.setMeasurementUnit("MeasurementUnit1");
		aggregatorProductDaoObject2.setRequestTimeStamp(1567242403905L);
		aggregatorProductDaoServiceImpl.checkExistenceAndDumpData(aggregatorProductDaoObject2);
	}
	
	@Test
	public void testDumpDataForUpdationNonStaleDataEqual() throws AggregatorException {
		List<AggregatorProductDaoObject> aggregatorProductDaoObjectList = new ArrayList<>();
		
		AggregatorProductDaoObject aggregatorProductDaoObject = new AggregatorProductDaoObject();
		aggregatorProductDaoObject.setName("Name1");
		aggregatorProductDaoObject.setRequestTimeStamp(Long.valueOf(1567242403906L));
		aggregatorProductDaoObject.setProvider("provider1");
		aggregatorProductDaoObject.setAvailable(true);
		aggregatorProductDaoObject.setDescription("Description1");
		aggregatorProductDaoObject.setId("UUID1");
		aggregatorProductDaoObject.setMeasurementUnit("MeasurementUnit1");
		aggregatorProductDaoObject.setNumberOfTimesUpdated(0L);
		aggregatorProductDaoObject.setRequestTimeStamp(1567242403906L);		
		aggregatorProductDaoObjectList.add(aggregatorProductDaoObject);
		
		when(aggregatorProductDaoObjectRepository.findRecord(anyString(),anyString())).thenReturn(aggregatorProductDaoObjectList);
		doAnswer(new Answer<Void>() {
			 public Void answer(InvocationOnMock invocation) {
				 Object[] args = invocation.getArguments();
				 AggregatorProductDaoObject aggregatorProductDaoObject = (AggregatorProductDaoObject) args[0];
				 assertEquals("Name1", aggregatorProductDaoObject.getName());
				 assertEquals(Long.valueOf(1567242403908L) , aggregatorProductDaoObject.getRequestTimeStamp());
				 assertEquals( "provider1", aggregatorProductDaoObject.getProvider());
				 assertTrue(aggregatorProductDaoObject.getAvailable());
				 assertEquals("Description1",aggregatorProductDaoObject.getDescription());
				 assertEquals("UUID1",aggregatorProductDaoObject.getId());
				 assertEquals("MeasurementUnit1",aggregatorProductDaoObject.getMeasurementUnit());
				 assertEquals(Long.valueOf(0L), aggregatorProductDaoObject.getNumberOfTimesUpdated());
				 assertTrue(null == aggregatorProductDaoObject.getUpdatedDateTime());
				 return null;
			 }
		}).when(aggregatorProductDaoObjectRepository).save(any(AggregatorProductDaoObject.class));
		
		AggregatorProductDaoObject aggregatorProductDaoObject2 = new AggregatorProductDaoObject();
		aggregatorProductDaoObject2.setName("Name1");
		aggregatorProductDaoObject2.setRequestTimeStamp(Long.valueOf(1567242403907L));
		aggregatorProductDaoObject2.setProvider("provider1");
		aggregatorProductDaoObject2.setAvailable(true);
		aggregatorProductDaoObject2.setDescription("Description1");
		aggregatorProductDaoObject2.setId("UUID1");
		aggregatorProductDaoObject2.setMeasurementUnit("MeasurementUnit1");
		aggregatorProductDaoObject2.setRequestTimeStamp(1567242403908L);
		aggregatorProductDaoServiceImpl.checkExistenceAndDumpData(aggregatorProductDaoObject2);
	}
}
