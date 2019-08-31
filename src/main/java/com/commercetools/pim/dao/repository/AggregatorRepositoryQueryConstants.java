package com.commercetools.pim.dao.repository;

/**
 * 
 * @author Jacob
 *
 */
public class AggregatorRepositoryQueryConstants {

	/**
	 * private constructor to avoid initiation
	 */
	private AggregatorRepositoryQueryConstants() {	}

	public static final String FIND_RECORD_USING_NAME_AND_PROVIDER = "SELECT a FROM AggregatorProductDaoObject a WHERE a.name = :name and a.provider = :provider";
	public static final String FIND_RECORD_CORRESPONDING_TO_DATES = "SELECT a FROM AggregatorDailyStatsViewDaoObject a";
	public static final String FIND_RECORD_THAT_ARE_AVAILABLE = "SELECT a FROM AggregatorProductDaoObject a WHERE a.available = true";
}
