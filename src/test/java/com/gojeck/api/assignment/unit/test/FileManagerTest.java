package com.gojeck.api.assignment.unit.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.gojeck.api.assignment.utils.FileManager;

public class FileManagerTest {

	private static List<String> expectedLines = new ArrayList<>();

	static{
		expectedLines.add("https://reqres.in/api/users/3");
		expectedLines.add("");
		expectedLines.add("https://reqres.in/api/users/1");
	}

	@Test
	public void testFileRead(){
		String filePath1 = getClass().getResource("/FileManagerTest.txt").toString()
				.replace("%20", " ").replace("file:","").replaceFirst("/", "");

		FileManager fm = new FileManager(new File(filePath1));
		List<String> lines = fm.readLinesFromFile();
		Assert.assertEquals(lines.size(), 3);
		Assert.assertEquals(lines, expectedLines);
	}
}
