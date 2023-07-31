package com.onehealth.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.onehealth.entity.Tests;

/**
 * The TestRepository interface provides CRUD operations for the Tests entity.
 * It extends the JpaRepository interface, which provides standard JPA methods for data access.
 */
public interface TestRepository extends JpaRepository<Tests, Long> {
	
	/**
	 * Custom query to find tests by lab ID.
	 *
	 * @param lab_id The ID of the lab for which tests need to be retrieved.
	 * @return A list of Tests matching the provided lab ID.
	 */
	List<Tests> findByLabId(long lab_id);
}
