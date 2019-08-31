package com.commercetools.pim.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.commercetools.pim.dao.model.AggregatorProductDaoObject;

/**
 * 
 * @author Jacob
 *
 */
@Repository
public interface AggregatorProductDaoObjectRepository extends JpaRepository<AggregatorProductDaoObject, String>{

	/**
	 * find record corresponding to unique name and provider
	 * @param name
	 * @param provider
	 * @return
	 */
	@Query(AggregatorRepositoryQueryConstants.FIND_RECORD_USING_NAME_AND_PROVIDER)
	List<AggregatorProductDaoObject> findRecord(@Param("name")String name, @Param("provider")String provider);

	/**
	 * Find all products with available = true
	 * @return
	 */
	@Query(AggregatorRepositoryQueryConstants.FIND_RECORD_THAT_ARE_AVAILABLE)
	List<AggregatorProductDaoObject> getAvailableProducts();

}
