package com.commercetools.pim.dao.service;



import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.commercetools.pim.dao.model.AggregatorDailyStatsViewDaoObject;
import com.commercetools.pim.dao.model.AggregatorProductDaoObject;
import com.commercetools.pim.dao.repository.AggregatorDailyStatsViewRepository;
import com.commercetools.pim.dao.repository.AggregatorProductDaoObjectRepository;

/**
 * 
 * @author Jacob
 *
 */
@Component
public class AggregatorProductDaoServiceImpl implements AggregatorProductDaoService {
	@Autowired
	private AggregatorProductDaoObjectRepository aggregatorProductRepository;
	
	@Autowired
	private AggregatorDailyStatsViewRepository aggregatorDailyStatsViewRepository;
	
	/**
	 * Check if the record exists otherwise create new one
	 * @param aggregatorProductDaoObject
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE)
	@Override
	public void checkExistenceAndDumpData(AggregatorProductDaoObject aggregatorProductDaoObject) {
		
		AggregatorProductDaoObject aggregatorProductDaoObjectToBeUpdated = aggregatorProductDaoObject;
		
		List<AggregatorProductDaoObject> aggregatorProductDaoObjectFromBackendList =
				aggregatorProductRepository.findRecord(aggregatorProductDaoObject.getName(),
						aggregatorProductDaoObject.getProvider());
		
		if(null != aggregatorProductDaoObjectFromBackendList && !aggregatorProductDaoObjectFromBackendList.isEmpty()) {			
			
			aggregatorProductDaoObjectToBeUpdated = aggregatorProductDaoObjectFromBackendList.get(0);
			
			populateUpdatedAggregatorProductDaoObject(aggregatorProductDaoObjectToBeUpdated,aggregatorProductDaoObject);
			
		}
		
		aggregatorProductRepository.save(aggregatorProductDaoObjectToBeUpdated);
	}

	/**
	 * populate values for only non stale and non equal records
	 * @param aggregatorProductDaoObjectToBeUpdated
	 * @param aggregatorProductDaoObject
	 */
	private void populateUpdatedAggregatorProductDaoObject(
			AggregatorProductDaoObject aggregatorProductDaoObjectToBeUpdated,
			AggregatorProductDaoObject aggregatorProductDaoObject) {
		
		if(aggregatorProductDaoObjectToBeUpdated.getRequestTimeStamp() 
				>= aggregatorProductDaoObject.getRequestTimeStamp()) {//checking for Stale request
			if(!aggregatorProductDaoObjectToBeUpdated.equals(aggregatorProductDaoObject)) {
				updateDateTimeAndUpdateCount(aggregatorProductDaoObjectToBeUpdated);				
			}
			return;
		}
		
		if(aggregatorProductDaoObjectToBeUpdated.equals(aggregatorProductDaoObject)) {// reducing processing and only updating request timestamp
			aggregatorProductDaoObjectToBeUpdated.setRequestTimeStamp(aggregatorProductDaoObject.getRequestTimeStamp());
			return;
		}		
		
		aggregatorProductDaoObjectToBeUpdated.setAvailable(aggregatorProductDaoObject.getAvailable());
		aggregatorProductDaoObjectToBeUpdated.setDescription(aggregatorProductDaoObject.getDescription());
		aggregatorProductDaoObjectToBeUpdated.setMeasurementUnit(aggregatorProductDaoObject.getMeasurementUnit());
		aggregatorProductDaoObjectToBeUpdated.setRequestTimeStamp(aggregatorProductDaoObject.getRequestTimeStamp());
		updateDateTimeAndUpdateCount(aggregatorProductDaoObjectToBeUpdated);	
		
	}

	private void updateDateTimeAndUpdateCount(AggregatorProductDaoObject aggregatorProductDaoObjectToBeUpdated) {
		aggregatorProductDaoObjectToBeUpdated.setNumberOfTimesUpdated(aggregatorProductDaoObjectToBeUpdated.getNumberOfTimesUpdated() + 1);
		aggregatorProductDaoObjectToBeUpdated.setUpdatedDateTime( new Date(
				Calendar.getInstance().getTimeInMillis()));
		}

	/**
	 * get all available products
	 * @return
	 */
	@Override
	public List<AggregatorProductDaoObject> getAvailableProducts() {
		return aggregatorProductRepository.getAvailableProducts();
	}

	/**
	 * get daily statistics about creation and update of product
	 * @return
	 */
	@Override
	public List<AggregatorDailyStatsViewDaoObject> getDailyStatistics() {
		return aggregatorDailyStatsViewRepository.findDailyStatistics();
	}

	/**
	 * @param aggregatorProductRepository the aggregatorProductRepository to set
	 */
	public void setAggregatorProductRepository(AggregatorProductDaoObjectRepository aggregatorProductRepository) {
		this.aggregatorProductRepository = aggregatorProductRepository;
	}


	/**
	 * @param aggregatorDailyStatsViewRepository the aggregatorDailyStatsViewRepository to set
	 */
	public void setAggregatorDailyStatsViewRepository(
			AggregatorDailyStatsViewRepository aggregatorDailyStatsViewRepository) {
		this.aggregatorDailyStatsViewRepository = aggregatorDailyStatsViewRepository;
	}

	
}
