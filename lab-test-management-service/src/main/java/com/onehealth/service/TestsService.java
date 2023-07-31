package com.onehealth.service;

import java.util.List;
import com.onehealth.dto.TestsUpdateRequest;
import com.onehealth.entity.Tests;
import com.onehealth.exception.DatabaseException;
import com.onehealth.exception.TestNotFoundException;

/**
 * The TestsService interface provides methods to interact with the Tests entity.
 * It defines the service layer operations for managing tests.
 */
public interface TestsService {
	
	/**
	 * Retrieve a list of all tests.
	 *
	 * @return A list of all available tests.
	 * @throws DatabaseException If there's an issue accessing the database.
	 */
	List<Tests> getAllTests() throws DatabaseException;

	/**
	 * Retrieve a test by its ID.
	 *
	 * @param test_id The ID of the test to retrieve.
	 * @return The test with the provided ID.
	 * @throws TestNotFoundException If the test with the given ID is not found.
	 * @throws DatabaseException     If there's an issue accessing the database.
	 */
	Tests getTestById(long test_id) throws TestNotFoundException, DatabaseException;

	/**
	 * Delete a test by its ID.
	 *
	 * @param test_id The ID of the test to delete.
	 * @throws TestNotFoundException If the test with the given ID is not found.
	 * @throws DatabaseException     If there's an issue accessing the database.
	 */
	void deleteTest(long test_id) throws DatabaseException, TestNotFoundException;

	/**
	 * Update the details of a test.
	 *
	 * @param test The test with updated details to be saved.
	 * @throws TestNotFoundException If the test with the given ID is not found.
	 * @throws DatabaseException     If there's an issue accessing the database.
	 */
	void updateTestDetails(TestsUpdateRequest test) throws DatabaseException, TestNotFoundException;

	/**
	 * Retrieve a list of tests based on the lab ID.
	 *
	 * @param lab_id The ID of the lab for which tests need to be retrieved.
	 * @return A list of tests associated with the provided lab ID.
	 * @throws DatabaseException If there's an issue accessing the database.
	 */
	List<Tests> getAllTestByLabId(long lab_id) throws DatabaseException;

	/**
	 * Add a new test to the database.
	 *
	 * @param test The new test to be added.
	 * @throws DatabaseException If there's an issue accessing the database.
	 */
	void addNewTest(Tests test) throws DatabaseException;
}
