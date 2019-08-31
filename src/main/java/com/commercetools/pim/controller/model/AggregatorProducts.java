package com.commercetools.pim.controller.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author Jacob
 *
 */

@MappedSuperclass
@JsonInclude(Include.NON_NULL)
public class AggregatorProducts {
	
	@Column(name = "NAME")
	protected String name;
	
	@Column(name = "DESCRIPTION")
	protected String description;
	
	@Column(name = "PROVIDER")
	protected String provider;
		
	@Column(name = "MEASUREMENT_UNIT")
	protected String measurementUnit;
	

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the provider
	 */
	public String getProvider() {
		return provider;
	}

	/**
	 * @param provider the provider to set
	 */
	public void setProvider(String provider) {
		this.provider = provider;
	}

	/**
	 * @return the measurementUnit
	 */
	public String getMeasurementUnit() {
		return measurementUnit;
	}

	/**
	 * @param measurementUnit the measurementUnit to set
	 */
	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}
	
}
