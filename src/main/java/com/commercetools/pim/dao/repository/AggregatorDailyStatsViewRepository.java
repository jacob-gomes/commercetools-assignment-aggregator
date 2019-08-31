package com.commercetools.pim.dao.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.commercetools.pim.dao.model.AggregatorDailyStatsViewDaoObject;

/**
 * 
 * @author Jacob
 *
 */
@Repository
public interface AggregatorDailyStatsViewRepository extends JpaRepository<AggregatorDailyStatsViewDaoObject, Date>{

	/**
	 * Find daily statistics
	 * @return
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE)
	@Query(AggregatorRepositoryQueryConstants.FIND_RECORD_CORRESPONDING_TO_DATES)
	List<AggregatorDailyStatsViewDaoObject> findDailyStatistics();

}
