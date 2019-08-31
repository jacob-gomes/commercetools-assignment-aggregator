package com.commercetools.pim.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.commercetools.pim.exception.AggregatorException;
import com.commercetools.pim.service.AggregatorService;

/**
 * 
 * @author Jacob
 *
 */
@Component
public class AggregatorKafkaConsumer {
	
	Logger logger = LoggerFactory.getLogger(AggregatorKafkaConsumer.class);
	
	@Autowired
	private AggregatorService aggregatorService;

	/**
	 * Consumer of the topic
	 * @param requestMessage
	 * @throws AggregatorException 
	 */
	@Async
	@KafkaListener(topics = "${commercetools.topic}")
	public void processMessage(String requestMessage) throws AggregatorException {
		try {
			logger.debug("Recieved message: {}", requestMessage);
			aggregatorService.dumpDataInBackend(requestMessage);
		}catch(Exception e) {
			throw new AggregatorException("Exception Occured while dumping data");
		}		
	}
}
