package com.gojeck.api.assignment.tests;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.gojeck.api.assignment.common.RestResponseComparator;
import com.gojeck.api.assignment.utils.CustomLogger;
import com.gojeck.api.assignment.utils.FileManager;

/**
 * 
 * @author Prerit Jain
 *
 */
public class TestRestEndPoints extends RestResponseComparator{
	
	private static String filePath1;
	private static String filePath2;

	private static FileManager fileManager1;
	private static FileManager fileManager2;
	
	protected List<String> endPointsFromFile1;
	protected List<String> endPointsFromFile2;
	
	@BeforeClass
	private void beforeClass(){
		overrideFiles();
		
		fileManager1 = new FileManager(new File(filePath1));
		fileManager2 = new FileManager(new File(filePath2));
		
		endPointsFromFile1 = fileManager1.readLinesFromFile();
		endPointsFromFile2 = fileManager2.readLinesFromFile();
	}
	
	@Test
	public void validateTestDataFiles(){
		Assert.assertEquals(endPointsFromFile1.size(), endPointsFromFile2.size(), 
				"Total number of end points are not same in both the file. Please correct the test data.");
		CustomLogger.info("Successfully validated number of end points in both the files");
	}
	
	@Test
	public void compare() throws InterruptedException{
		runBatchCompare(endPointsFromFile1, endPointsFromFile2);
		Assert.assertFalse(hasFailures(), "Missmatch in API reponses");
	}
	
	public void overrideFiles(){
		String fileOverride1 = System.getProperty("file1");
		String fileOverride2 = System.getProperty("file2");
		if(!StringUtils.isEmpty(fileOverride1) && !StringUtils.isEmpty(fileOverride2)){
			filePath1 = fileOverride1;
			filePath2 = fileOverride2;
			CustomLogger.info("*** RUNNING TESTS WITH EXTERNALLY PASSED FILE NAMES ***");
		}else{
			filePath1 = getClass().getResource("/RestEndPoint_1.txt").toString()
					.replace("%20", " ").replace("file:","").replaceFirst("/", "");
			filePath2 = getClass().getResource("/RestEndPoint_2.txt").toString()
					.replace("%20", " ").replace("file:","").replaceFirst("/", "");
			
			CustomLogger.info("*** RUNNING TESTS WITH DEFAULT FILE NAMES ***");
		}
		
		CustomLogger.info("File1: "+filePath1);
		CustomLogger.info("File2: "+filePath2);
	}
}
