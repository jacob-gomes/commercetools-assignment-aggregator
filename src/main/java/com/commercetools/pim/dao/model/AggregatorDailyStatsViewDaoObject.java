package com.commercetools.pim.dao.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Jacob
 *
 */
@Entity
@Table(name = "DAILY_PRODUCT_INFORMATION")
public class AggregatorDailyStatsViewDaoObject {
	@Id
	@Column(name = "CREATED_DATETIME")
	private Date date;
	
	@Column(name = "NUMBER_OF_RECORD_CREATED")
	private Integer numberOfRecordsCreated;
	
	@Column(name = "NUMBER_OF_RECORD_UPDATED")
	private Integer numberOfRecordsUpdated;

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the numberOfRecordsCreated
	 */
	public Integer getNumberOfRecordsCreated() {
		return numberOfRecordsCreated;
	}

	/**
	 * @param numberOfRecordsCreated the numberOfRecordsCreated to set
	 */
	public void setNumberOfRecordsCreated(Integer numberOfRecordsCreated) {
		this.numberOfRecordsCreated = numberOfRecordsCreated;
	}

	/**
	 * @return the numberOfRecordsUpdated
	 */
	public Integer getNumberOfRecordsUpdated() {
		return numberOfRecordsUpdated;
	}

	/**
	 * @param numberOfRecordsUpdated the numberOfRecordsUpdated to set
	 */
	public void setNumberOfRecordsUpdated(Integer numberOfRecordsUpdated) {
		this.numberOfRecordsUpdated = numberOfRecordsUpdated;
	}
	
	
}
