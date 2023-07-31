package com.onehealth.serviceImplementation;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.onehealth.dto.TestsUpdateRequest;
import com.onehealth.entity.Tests;
import com.onehealth.exception.DatabaseException;
import com.onehealth.exception.TestNotFoundException;
import com.onehealth.repository.TestRepository;
import com.onehealth.service.TestsService;
/**
 * The TestsServiceImplementation class is an implementation of the TestsService interface.
 * It provides the business logic for handling test-related operations and interacts with the database through the TestRepository.
 */
@Service
public class TestsServiceImplementation implements TestsService{

    private static final Logger logger = LoggerFactory.getLogger(TestsService.class);
	
    @Autowired
    private TestRepository testRepository;
	
    /**
     * Retrieve a list of all tests.
     *
     * @return A list of all available tests.
     * @throws DatabaseException If there's an issue accessing the database.
     */
    @Override
    public List<Tests> getAllTests() throws DatabaseException {
        logger.info("Fetching all tests");
        return testRepository.findAll();
    }

    /**
     * Retrieve a test by its ID.
     *
     * @param test_id The ID of the test to retrieve.
     * @return The test with the provided ID.
     * @throws TestNotFoundException If the test with the given ID is not found.
     */
    @Override
    public Tests getTestById(long test_id) throws TestNotFoundException {
        logger.info("Fetching test with test_id: {}", test_id);
        try {
            Tests test = testRepository.findById(test_id)
                    .orElseThrow(() -> new TestNotFoundException("Test Not Found With test_id: " + test_id));
            logger.info("Returning test with test_id: {}", test_id);
            return test;
        } catch (TestNotFoundException ex) {
            logger.warn("Test with test_id {} not found.", test_id);
            logger.error("Operation failed !!");
            throw ex;
        } catch (Exception ex) {
            logger.error("An error occurred while fetching test with test_id: {}", test_id, ex);
            throw ex;
        }
    }

    /**
     * Delete a test by its ID.
     *
     * @param test_id The ID of the test to delete.
     * @throws TestNotFoundException If the test with the given ID is not found.
     * @throws DatabaseException If there's an issue accessing the database.
     */
    @Override
    public void deleteTest(long test_id) throws DatabaseException, TestNotFoundException {
        boolean isValidId = testRepository.existsById(test_id);
        if (isValidId) {
            logger.info("Deleting test with test_id: {}", test_id);
            testRepository.deleteById(test_id);
        } else {
            logger.warn("Test with test_id {} not found.", test_id);
            logger.error("Operation failed !!");
            throw new TestNotFoundException("Test Not Found with test_id : " + test_id);
        }
    }

    /**
     * Update the details of a test.
     *
     * @param test The test with updated details to be saved.
     * @throws TestNotFoundException If the test with the given ID is not found.
     * @throws DatabaseException If there's an issue accessing the database.
     */
    @Override
    public void updateTestDetails(TestsUpdateRequest test) throws DatabaseException, TestNotFoundException {
        logger.info("Updating test with test_id: {}", test.getTest_id());
        boolean isValidId = testRepository.existsById(test.getTest_id());
        if (isValidId) {
            logger.info("Deleting test with test_id: {}", test.getTest_id());
            Tests updateTest = new Tests();
            updateTest.setTest_id(test.getTest_id());
            updateTest.setTest_name(test.getTest_name());
            updateTest.setPrice(test.getPrice());
            updateTest.setGov_appro_cert_path(test.getGov_appro_cert_path());
            updateTest.setHome_sample(test.getHome_sample());
            updateTest.setTest_approval(test.isTest_approval());
            updateTest.setTest_description(test.getTest_description());
            updateTest.setLabId(test.getLabId());
            // TODO Auto-generated method stub
            testRepository.save(updateTest);
        } else {
            logger.warn("Test with test_id {} not found.", test.getTest_id());
            logger.error("Operation failed !!");
            throw new TestNotFoundException("Test Not Found with : " + test.getTest_id());
        }
    }

    /**
     * Retrieve a list of tests based on the lab ID.
     *
     * @param lab_id The ID of the lab for which tests need to be retrieved.
     * @return A list of tests associated with the provided lab ID.
     * @throws DatabaseException If there's an issue accessing the database.
     */
    @Override
    public List<Tests> getAllTestByLabId(long lab_id) throws DatabaseException {
        logger.info("Fetching all tests for lab with lab_id: {}", lab_id);
        return testRepository.findByLabId(lab_id);
    }

    /**
     * Add a new test to the database.
     *
     * @param test The new test to be added.
     * @throws DatabaseException If there's an issue accessing the database.
     */
    @Override
    public void addNewTest(Tests test) throws DatabaseException {
        logger.info("Adding a new test");
        // TODO Auto-generated method stub
        testRepository.save(test);
    }
}
