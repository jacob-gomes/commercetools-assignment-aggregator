package com.commercetools.pim.dao.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.commercetools.pim.controller.model.AggregatorProducts;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author Jacob
 *
 */
@Entity
@Table(name = "PRODUCT")
public class AggregatorProductDaoObject extends AggregatorProducts{

	@JsonIgnore
	@Id
	@Column(name = "ID")
	protected String id;
	
	@JsonIgnore
	@Column(name = "AVAILABLE")
	private Boolean available;
	
	@JsonIgnore
	@Column(name = "CREATED_DATETIME")
	private Date createdDateTime;
	
	@JsonIgnore
	@Column(name = "UPDATED_DATETIME")
	private Date updatedDateTime;
	
	@JsonIgnore
	@Column(name = "LAST_UPDATED_REQUEST_TIMESTAMP")
	private Long requestTimeStamp;
	
	@JsonIgnore
	@Column(name = "NUMBER_OF_TIMES_UPDATED")
	protected Long numberOfTimesUpdated;
	
	/**
	 * Checking for equality
	 * @param aggregatorProductDaoObject
	 * @return
	 */
	@JsonIgnore
	@Override
	public boolean equals(Object object) {
		
		if(null == object || !AggregatorProductDaoObject.class.equals(object.getClass())) {
			return false;
		}
		
		AggregatorProductDaoObject aggregatorProducts = (AggregatorProductDaoObject)object;
		
		return null != this.name 
				&& null != this.description 
				&& null != this.provider 
				&& null != this.measurementUnit
				&& null != this.available 
				&& this.name.equals(aggregatorProducts.getName()) 
				&& this.description.equals(aggregatorProducts.getDescription())
				&& this.provider.equals(aggregatorProducts.getProvider()) 
				&& this.measurementUnit.equals(aggregatorProducts.getMeasurementUnit())
				&& this.available.equals(aggregatorProducts.getAvailable()) ;
	}
	
	/**
	 * Check if any field is empty
	 * @return
	 */
	@JsonIgnore
	public boolean isEmpty() {		
		return (null == this.name || this.name.isEmpty())
				&& (null == this.description || this.description.isEmpty())
				&& (null == this.provider || this.provider.isEmpty())
				&& (null == this.measurementUnit || this.measurementUnit.isEmpty())
				&& null == this.available;
	}
	
	@JsonIgnore
	@Override
	public int hashCode() {
		return 0;		
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return the available
	 */
	@JsonIgnore
	public Boolean getAvailable() {
		return available;
	}

	/**
	 * @param available the available to set
	 */
	public void setAvailable(Boolean available) {
		this.available = available;
	}


	/**
	 * @param createdDateTime the createdDateTime to set
	 */
	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	/**
	 * @return the updatedDateTime
	 */
	public Date getUpdatedDateTime() {
		return updatedDateTime;
	}

	/**
	 * @param updatedDateTime the updatedDateTime to set
	 */
	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	/**
	 * @return the requestTimeStamp
	 */
	public Long getRequestTimeStamp() {
		return requestTimeStamp;
	}

	/**
	 * @param requestTimeStamp the requestTimeStamp to set
	 */
	public void setRequestTimeStamp(Long requestTimeStamp) {
		this.requestTimeStamp = requestTimeStamp;
	}


	/**
	 * @return the numberOfTimesUpdated
	 */
	public Long getNumberOfTimesUpdated() {
		return numberOfTimesUpdated;
	}

	/**
	 * @param numberOfTimesUpdated the numberOfTimesUpdated to set
	 */
	public void setNumberOfTimesUpdated(Long numberOfTimesUpdated) {
		this.numberOfTimesUpdated = numberOfTimesUpdated;
	}

}
