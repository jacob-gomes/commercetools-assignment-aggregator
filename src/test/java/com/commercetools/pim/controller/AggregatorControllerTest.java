package com.commercetools.pim.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.commercetools.pim.AggregatorCommonSetUpClass;
import com.commercetools.pim.controller.model.AggregatorDailyStatistics;
import com.commercetools.pim.controller.model.AggregatorProducts;
import com.commercetools.pim.dao.model.AggregatorDailyStatsViewDaoObject;
import com.commercetools.pim.dao.model.AggregatorProductDaoObject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AggregatorControllerTest extends AggregatorCommonSetUpClass {
	
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void testPositiveConditionForDailyStats() {
		List<AggregatorDailyStatsViewDaoObject> aggregatorDailyStatsViewDaoObjectList =
				new ArrayList<>();
		Date date = new Date(1567226111216L);
		
		AggregatorDailyStatsViewDaoObject aggregatorDailyStatsViewDaoObject = new AggregatorDailyStatsViewDaoObject();
		aggregatorDailyStatsViewDaoObject.setDate(date);
		aggregatorDailyStatsViewDaoObject.setNumberOfRecordsCreated(11);
		aggregatorDailyStatsViewDaoObject.setNumberOfRecordsUpdated(3);
		
		aggregatorDailyStatsViewDaoObjectList.add(aggregatorDailyStatsViewDaoObject);
		
		when(aggregatorDailyStatsViewRepository.findDailyStatistics()).thenReturn(aggregatorDailyStatsViewDaoObjectList);
		ResponseEntity<Map<String, AggregatorDailyStatistics>> responseEntity = aggregatorController.getDailyStatistics();
		Map<String, AggregatorDailyStatistics> responseBodyAggregatorDailyStatisticsMap = responseEntity.getBody();
		
		assertTrue(null != responseBodyAggregatorDailyStatisticsMap);
		assertEquals(1 , responseBodyAggregatorDailyStatisticsMap.size());
		
		assertTrue(responseBodyAggregatorDailyStatisticsMap.keySet().contains(date.toString()));
		AggregatorDailyStatistics aggregatorDailyStatistics = responseBodyAggregatorDailyStatisticsMap.get(date.toString());
		
		assertEquals(Integer.valueOf(11), aggregatorDailyStatistics.getProductsCreated());
		assertEquals(Integer.valueOf(3), aggregatorDailyStatistics.getProuctsUpdated());
	}
	
	@Test
	public void testEmptyDatabaseForDailyStats() {
		when(aggregatorDailyStatsViewRepository.findDailyStatistics()).thenReturn(new ArrayList<AggregatorDailyStatsViewDaoObject>());
		ResponseEntity<Map<String, AggregatorDailyStatistics>> responseEntity = aggregatorController.getDailyStatistics();
		Map<String, AggregatorDailyStatistics> responseBodyAggregatorDailyStatisticsMap = responseEntity.getBody();
		assertTrue(null == responseBodyAggregatorDailyStatisticsMap);
	}
	
	@Test
	public void testPostitveConditionForAvailableProducts() {
		List<AggregatorProductDaoObject> aggregatorProductDaoObjectList = new ArrayList<>();
		
		AggregatorProductDaoObject aggregatorProductDaoObject = new AggregatorProductDaoObject();
		aggregatorProductDaoObject.setAvailable(true);
		aggregatorProductDaoObject.setName("name1");
		aggregatorProductDaoObject.setProvider("provider1");
		aggregatorProductDaoObject.setDescription("Description1");
		aggregatorProductDaoObject.setMeasurementUnit("measurementUnit1");
		
		AggregatorProductDaoObject aggregatorProductDaoObject2 = new AggregatorProductDaoObject();
		aggregatorProductDaoObject2.setAvailable(true);
		aggregatorProductDaoObject2.setName("name2");
		aggregatorProductDaoObject2.setProvider("provider2");
		aggregatorProductDaoObject2.setDescription("Description2");
		aggregatorProductDaoObject2.setMeasurementUnit("measurementUnit2");
		
		aggregatorProductDaoObjectList.add(aggregatorProductDaoObject);
		aggregatorProductDaoObjectList.add(aggregatorProductDaoObject2);
		
		when(aggregatorProductDaoObjectRepository.getAvailableProducts()).thenReturn(aggregatorProductDaoObjectList);
		
		ResponseEntity<List<AggregatorProducts>>  responseEntity = aggregatorController.getAvailableProducts();
		
		List<AggregatorProducts> aggregatorProductsList = responseEntity.getBody();
		assertTrue(null != aggregatorProductsList);
		assertEquals( 2L, aggregatorProductsList.size());
		
		AggregatorProducts aggregatorProducts = aggregatorProductsList.get(0);
		assertEquals("name1", aggregatorProducts.getName());
		assertEquals("provider1",aggregatorProducts.getProvider());
		assertEquals("Description1",aggregatorProducts.getDescription());
		assertEquals("measurementUnit1",aggregatorProducts.getMeasurementUnit());
		
		AggregatorProducts aggregatorProducts2 = aggregatorProductsList.get(1);
		assertEquals("name2", aggregatorProducts2.getName());
		assertEquals("provider2",aggregatorProducts2.getProvider());
		assertEquals("Description2",aggregatorProducts2.getDescription());
		assertEquals("measurementUnit2",aggregatorProducts2.getMeasurementUnit());
		
	}
	
	@Test
	public void testEmptyDatabaseForAvailableProducts() {
		when(aggregatorProductDaoObjectRepository.getAvailableProducts()).thenReturn(new ArrayList<AggregatorProductDaoObject>());
		ResponseEntity<List<AggregatorProducts>>  responseEntity = aggregatorController.getAvailableProducts();
		
		List<AggregatorProducts> aggregatorProductsList = responseEntity.getBody();
		assertTrue(aggregatorProductsList != null);
		assertEquals(0, aggregatorProductsList.size());
	}
}
