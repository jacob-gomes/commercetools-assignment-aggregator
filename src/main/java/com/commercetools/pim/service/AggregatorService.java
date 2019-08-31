package com.commercetools.pim.service;

import java.util.List;
import java.util.Map;

import com.commercetools.pim.controller.model.AggregatorDailyStatistics;
import com.commercetools.pim.controller.model.AggregatorProducts;
import com.commercetools.pim.exception.AggregatorException;

/**
 * 
 * @author Jacob
 *
 */
public interface AggregatorService {
	/**
	 * Receives data from Kafka and dumps in DB
	 * @param requestMessage
	 * @throws AggregatorException 
	 */
	public void dumpDataInBackend(String requestMessage) throws AggregatorException;

	/**
	 * Fetches available products from DB and passes to Controller
	 * @return
	 */
	public List<AggregatorProducts> getAvailableProducts();

	/**
	 * Fetches daily statistics from DB and passes it to Controller
	 * @return
	 */
	public Map<String, AggregatorDailyStatistics> getDailyStatistics();
}
