package com.onehealth.controller;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.onehealth.dto.TestsUpdateRequest;
import com.onehealth.entity.Tests;
import com.onehealth.exception.DatabaseException;
import com.onehealth.exception.TestNotFoundException;
import com.onehealth.service.TestsService;


/**
 * The TestsController class handles HTTP requests related to tests.
 * It defines various endpoints for CRUD operations and interacts with the TestsService.
 */

@RestController
@RequestMapping("/test")
public class TestsController {
	
	 private static final Logger logger = LoggerFactory.getLogger(TestsController.class);

	    @Autowired
	    private TestsService testsService;

	    /**
	     * Endpoint to fetch all tests.
	     *
	     * @return ResponseEntity containing a list of all available tests and HTTP status.
	     * @throws DatabaseException If there's an issue accessing the database.
	     */
	    @GetMapping("/allTest")
	    public ResponseEntity<List<Tests>> getAllTest() throws DatabaseException {
	        logger.info("Received request to fetch all tests");
	        List<Tests> tests = testsService.getAllTests();
	        logger.info("Returning {} tests", tests.size());
	        return new ResponseEntity<>(tests, HttpStatus.OK);
	    }

	    /**
	     * Endpoint to fetch a single test by its ID.
	     *
	     * @param test_id The ID of the test to retrieve.
	     * @return ResponseEntity containing the test with the provided ID and HTTP status.
	     * @throws TestNotFoundException If the test with the given ID is not found.
	     * @throws DatabaseException If there's an issue accessing the database.
	     */
	    @GetMapping("/singleTest")
	    public ResponseEntity<Tests> getTestById(@RequestParam("test_id") long test_id) throws TestNotFoundException, DatabaseException {
	        logger.info("Received request to fetch test with test_id: {}", test_id);
	        Tests test = testsService.getTestById(test_id);
	        if (test == null) {
	            logger.warn("Test with test_id {} not found.", test_id);
	            throw new TestNotFoundException("Test with test_id " + test_id + " not found");
	        }
	        logger.info("Returning test with test_id: {}", test_id);
	        return new ResponseEntity<>(test, HttpStatus.OK);
	    }

	    /**
	     * Endpoint to update the details of a test.
	     *
	     * @param request The request containing the updated test details.
	     * @return ResponseEntity with a success message and HTTP status.
	     * @throws DatabaseException If there's an issue accessing the database.
	     * @throws TestNotFoundException If the test with the given ID is not found.
	     */
	    @PutMapping("/updateTest")
	    public ResponseEntity<String> updateTestDetails(@RequestBody TestsUpdateRequest request) throws DatabaseException, TestNotFoundException {
	        logger.info("Received request to update test with test_id: {}", request.getTest_id());
	        testsService.updateTestDetails(request);
	        logger.info("Test with test_id {} updated successfully.", request.getTest_id());
	        return new ResponseEntity<>("Test having test_id : " + request.getTest_id() + " Updated Successfully !!", HttpStatus.OK);
	    }

	    /**
	     * Endpoint to delete a test by its ID.
	     *
	     * @param test_id The ID of the test to delete.
	     * @return ResponseEntity with a success message and HTTP status.
	     * @throws DatabaseException If there's an issue accessing the database.
	     * @throws TestNotFoundException If the test with the given ID is not found.
	     */
	    @DeleteMapping("/deleteTest")
	    public ResponseEntity<String> deleteTest(@RequestParam("test_id") long test_id) throws DatabaseException, TestNotFoundException {
	        logger.info("Received request to delete test with test_id: {}", test_id);
	        testsService.deleteTest(test_id);
	        logger.info("Test with test_id {} deleted successfully.", test_id);
	        return new ResponseEntity<>("Test having test_id : " + test_id + " Deleted Successfully !!", HttpStatus.OK);
	    }

	    /**
	     * Endpoint to fetch all tests based on the lab ID.
	     *
	     * @param lab_id The ID of the lab for which tests need to be retrieved.
	     * @return ResponseEntity containing a list of tests associated with the provided lab ID and HTTP status.
	     * @throws DatabaseException If there's an issue accessing the database.
	     */
	    @GetMapping("/inLab")
	    public ResponseEntity<?> getAllTestByLabId(@RequestParam("lab_id") long lab_id) throws DatabaseException {
	        logger.info("Received request to fetch tests for lab with lab_id: {}", lab_id);
	        List<Tests> tests = testsService.getAllTestByLabId(lab_id);
	        logger.info("Returning {} tests for lab with lab_id: {}", tests.size(), lab_id);
	        return new ResponseEntity<>(tests, HttpStatus.OK);
	    }

	    /**
	     * Endpoint to add a new test to the database.
	     *
	     * @param test The new test to be added.
	     * @return ResponseEntity with a success message and HTTP status.
	     * @throws DatabaseException If there's an issue accessing the database.
	     */
	    @PostMapping("/addNewTest")
	    public ResponseEntity<String> addNewTest(@RequestBody Tests test) throws DatabaseException {
	        logger.info("Received request to add a new test");
	        testsService.addNewTest(test);
	        logger.info("New test added successfully.");
	        return new ResponseEntity<>("Test Added Successfully !!", HttpStatus.CREATED);
	    }
}
