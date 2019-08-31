package com.commercetools.pim;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.commercetools.pim.controller.AggregatorController;
import com.commercetools.pim.dao.repository.AggregatorDailyStatsViewRepository;
import com.commercetools.pim.dao.repository.AggregatorProductDaoObjectRepository;
import com.commercetools.pim.dao.service.AggregatorProductDaoServiceImpl;
import com.commercetools.pim.kafka.AggregatorKafkaConsumer;
import com.commercetools.pim.service.AggregatorServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AggregatorCommonSetUpClass {
	@Autowired
	protected AggregatorController aggregatorController;
	
	@Autowired
	protected AggregatorKafkaConsumer aggregatorKafkaConsumer;
	
	@Autowired
	protected AggregatorProductDaoServiceImpl aggregatorProductDaoServiceImpl;
	
	@Autowired
	protected AggregatorServiceImpl aggregatorServiceImpl;

	@Mock
	protected AggregatorDailyStatsViewRepository aggregatorDailyStatsViewRepository;
	
	@Mock
	protected AggregatorProductDaoObjectRepository aggregatorProductDaoObjectRepository;

	
	public void setUp() {
		aggregatorProductDaoServiceImpl.setAggregatorDailyStatsViewRepository(aggregatorDailyStatsViewRepository);
		aggregatorProductDaoServiceImpl.setAggregatorProductRepository(aggregatorProductDaoObjectRepository);
	}
	
}
