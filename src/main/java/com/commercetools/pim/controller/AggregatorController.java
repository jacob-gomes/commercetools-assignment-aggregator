package com.commercetools.pim.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commercetools.pim.controller.model.AggregatorDailyStatistics;
import com.commercetools.pim.controller.model.AggregatorProducts;
import com.commercetools.pim.service.AggregatorService;

/**
 * 
 * @author Jacob
 *
 */
@Component
@RestController
public class AggregatorController {
	Logger logger = LoggerFactory.getLogger(AggregatorController.class);
	
	/**
	 * Rest API to provide all available products(i.e. records with Available = true)
	 * Output:
	 * [{
	 * 	"name":"NAME",
	 * 	"description":"DESCRIPTION",
	 * 	"provider":"PROVIDER",
	 *  "measurementUnit":"MEASUREMENTUNIT"
	 * }
	 * ...
	 * ]
	 */
	@Autowired
	private AggregatorService aggregatorService;

	@GetMapping(value = "/available-products",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AggregatorProducts>> getAvailableProducts() {
		logger.debug("Received call for /available-products");
		List<AggregatorProducts> availableProducts = aggregatorService.getAvailableProducts();
		
		return new ResponseEntity<>(availableProducts, HttpStatus.OK);
	}
	
	/**
	 * REST API to provides daily Statistics 
	 * Output:
	 * {
	 *  "DATE":{
	 *  "productsCreated": X
	 *  "productsUpdated": Y
	 *  }
	 *  ...  
	 * }
	 * 
	 * @return
	 */
	@GetMapping(value = "/daily-statistics",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, AggregatorDailyStatistics>> getDailyStatistics() {
		logger.debug("Received call for /daily-statistics");
		Map<String, AggregatorDailyStatistics> dailyStatsMap = aggregatorService.getDailyStatistics();
		
		return new ResponseEntity<>(dailyStatsMap, HttpStatus.OK);
	}
	
	
}
