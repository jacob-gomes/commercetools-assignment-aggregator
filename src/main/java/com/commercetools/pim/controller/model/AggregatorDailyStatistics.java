package com.commercetools.pim.controller.model;

import com.commercetools.pim.dao.model.AggregatorDailyStatsViewDaoObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * 
 * @author Jacob
 *
 */
@JsonInclude(Include.NON_NULL)
public class AggregatorDailyStatistics {
	
	private Integer productsCreated;
	
	private Integer prouctsUpdated;

	public AggregatorDailyStatistics() {}
	
	/**
	 * Parameterized Constructor
	 * @param aggregatorDailyStatsViewDaoObject
	 */
	public AggregatorDailyStatistics(AggregatorDailyStatsViewDaoObject aggregatorDailyStatsViewDaoObject) {
		this.productsCreated = aggregatorDailyStatsViewDaoObject.getNumberOfRecordsCreated();
		this.prouctsUpdated = aggregatorDailyStatsViewDaoObject.getNumberOfRecordsUpdated();
	}

	/**
	 * @return the productsCreated
	 */
	public Integer getProductsCreated() {
		return productsCreated;
	}

	/**
	 * @param productsCreated the productsCreated to set
	 */
	public void setProductsCreated(Integer productsCreated) {
		this.productsCreated = productsCreated;
	}

	/**
	 * @return the prouctsUpdated
	 */
	public Integer getProuctsUpdated() {
		return prouctsUpdated;
	}

	/**
	 * @param prouctsUpdated the prouctsUpdated to set
	 */
	public void setProuctsUpdated(Integer prouctsUpdated) {
		this.prouctsUpdated = prouctsUpdated;
	}

	
	
}
