package com.commercetools.pim.service.utils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.commercetools.pim.controller.model.AggregatorDailyStatistics;
import com.commercetools.pim.controller.model.AggregatorProducts;
import com.commercetools.pim.dao.model.AggregatorDailyStatsViewDaoObject;
import com.commercetools.pim.dao.model.AggregatorProductDaoObject;

/**
 * 
 * @author Jacob
 *
 */
public class AggregatorServiceUtils {	

	public static final String UUID = "uuid";

	public static final String NAME = "name";

	public static final String DESCRIPTION	= "description";

	public static final String PROVIDER = "provider";

	public static final String AVAILABLE = "available";

	public static final String MEASUREMENT_UNIT = "measurementUnit";

	public static final String REQUEST_TIMESTAMP_IN_MS = "requestTimeStampInMS";

	/**
	 * private constructor to avoid initiation
	 */
	private AggregatorServiceUtils() {}

	/**
	 * Populate Dao object from Json Object
	 * @param jsonObject
	 * @return
	 */
	public static AggregatorProductDaoObject populateAggregatorProductDaoObject(JSONObject jsonObject) {
		AggregatorProductDaoObject aggregatorProductDaoObject = new AggregatorProductDaoObject();

		if(null != jsonObject) {
			aggregatorProductDaoObject.setId(jsonObject.optString(UUID));
			aggregatorProductDaoObject.setName(jsonObject.optString(NAME));
			aggregatorProductDaoObject.setDescription(jsonObject.optString(DESCRIPTION));
			aggregatorProductDaoObject.setProvider(jsonObject.optString(PROVIDER));
			aggregatorProductDaoObject.setAvailable(jsonObject.optBoolean(AVAILABLE));
			aggregatorProductDaoObject.setMeasurementUnit(jsonObject.optString(MEASUREMENT_UNIT));
			aggregatorProductDaoObject.setRequestTimeStamp(jsonObject.optLong(REQUEST_TIMESTAMP_IN_MS));
			aggregatorProductDaoObject.setNumberOfTimesUpdated(0L);
			aggregatorProductDaoObject.setCreatedDateTime( new Date(
					Calendar.getInstance().getTimeInMillis()));
		}

		return aggregatorProductDaoObject;
	}

	/**
	 * Map Dao Object to Transfer object for Available products
	 * @param aggregatorProductDaoObjectList
	 * @return
	 */
	public static List<AggregatorProducts> mapDaoObjectToTransferObjectForAvailableProducts(
			List<AggregatorProductDaoObject> aggregatorProductDaoObjectList) {
		if(null != aggregatorProductDaoObjectList && !aggregatorProductDaoObjectList.isEmpty()) {
			List<AggregatorProducts> aggregatorProductList = new ArrayList<> ();
			aggregatorProductDaoObjectList.forEach(aggregatorProductList::add);
			return aggregatorProductList;
		}
		return new ArrayList<>();
	}

	/**
	 * Maps Dao object to Transfer Object for Daily Stats
	 * @param aggregatorDailyStatsViewDaoObjectList
	 * @return
	 */
	public static Map<String, AggregatorDailyStatistics> mapDaoObjectToTransferObjectForDailyStats(
			List<AggregatorDailyStatsViewDaoObject> aggregatorDailyStatsViewDaoObjectList) {
		if(null != aggregatorDailyStatsViewDaoObjectList && !aggregatorDailyStatsViewDaoObjectList.isEmpty()) {
			Map<String, AggregatorDailyStatistics> dailyStatsMap = new HashMap<>();
			aggregatorDailyStatsViewDaoObjectList.forEach(aggregatorDailyStatsViewDaoObject -> {
				AggregatorDailyStatistics aggregatorDailyStatistics = new AggregatorDailyStatistics(aggregatorDailyStatsViewDaoObject);
				dailyStatsMap.put(aggregatorDailyStatsViewDaoObject.getDate().toString(), aggregatorDailyStatistics);
			});
			return dailyStatsMap;
		}
		return null;
	}

}
