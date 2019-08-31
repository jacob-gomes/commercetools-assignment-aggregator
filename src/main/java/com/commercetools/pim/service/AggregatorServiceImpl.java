package com.commercetools.pim.service;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.commercetools.pim.controller.model.AggregatorDailyStatistics;
import com.commercetools.pim.controller.model.AggregatorProducts;
import com.commercetools.pim.dao.model.AggregatorDailyStatsViewDaoObject;
import com.commercetools.pim.dao.model.AggregatorProductDaoObject;
import com.commercetools.pim.dao.service.AggregatorProductDaoService;
import com.commercetools.pim.exception.AggregatorException;
import com.commercetools.pim.service.utils.AggregatorServiceUtils;

/**
 * 
 * @author Jacob
 *
 */
@Component
public class AggregatorServiceImpl implements AggregatorService {

	Logger logger = LoggerFactory.getLogger(AggregatorServiceImpl.class);

	@Autowired
	private AggregatorProductDaoService aggregatorProductDaoService;


	/**
	 * Receives data from Kafka and dumps in DB
	 * @param requestMessage
	 * @throws AggregatorException 
	 */
	@Override
	public void dumpDataInBackend(String requestMessage) throws AggregatorException {
		JSONObject jsonObject = null;
		AggregatorProductDaoObject aggregatorProductDaoObject;
		try {
			jsonObject = new JSONObject(requestMessage);
			aggregatorProductDaoObject = AggregatorServiceUtils.populateAggregatorProductDaoObject(jsonObject);
			if(!aggregatorProductDaoObject.isEmpty()) {
				aggregatorProductDaoService.checkExistenceAndDumpData(aggregatorProductDaoObject);
			}else {
				throw new AggregatorException("Cannot dump empty record");
			}
		} catch(Exception e) {
			logger.info("Error while parsing requestMessage");
			throw new AggregatorException(e.getMessage());
		}
	}


	/**
	 * Fetches available products from DB and passes to Controller
	 * @return
	 */
	@Override
	public List<AggregatorProducts> getAvailableProducts() {
		List<AggregatorProductDaoObject> aggregatorProductDaoObject = aggregatorProductDaoService.getAvailableProducts();

		return AggregatorServiceUtils.mapDaoObjectToTransferObjectForAvailableProducts(
				aggregatorProductDaoObject);
	}


	/**
	 * Fetches daily statistics from DB and passes it to Controller
	 * @return
	 */
	@Override
	public Map<String, AggregatorDailyStatistics> getDailyStatistics() {
		List<AggregatorDailyStatsViewDaoObject> aggregatorDailyStatsViewDaoObject = 
				aggregatorProductDaoService.getDailyStatistics();

		return AggregatorServiceUtils.mapDaoObjectToTransferObjectForDailyStats(
				aggregatorDailyStatsViewDaoObject);
	}

}
