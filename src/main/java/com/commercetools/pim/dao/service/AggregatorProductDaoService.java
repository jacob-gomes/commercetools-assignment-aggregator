package com.commercetools.pim.dao.service;

import java.util.List;

import com.commercetools.pim.dao.model.AggregatorDailyStatsViewDaoObject;
import com.commercetools.pim.dao.model.AggregatorProductDaoObject;

/**
 * 
 * @author Jacob
 *
 */
public interface AggregatorProductDaoService {

	/**
	 * Check if the record exists otherwise create new one
	 * @param aggregatorProductDaoObject
	 */
	void checkExistenceAndDumpData(AggregatorProductDaoObject aggregatorProductDaoObject);

	/**
	 * get all available products
	 * @return
	 */
	List<AggregatorProductDaoObject> getAvailableProducts();

	/**
	 * get daily statistics about creation and update of product
	 * @return
	 */
	List<AggregatorDailyStatsViewDaoObject> getDailyStatistics();

}
