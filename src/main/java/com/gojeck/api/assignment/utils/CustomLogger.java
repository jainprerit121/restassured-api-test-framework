package com.gojeck.api.assignment.utils;

import org.testng.Reporter;

/**
 * 
 * @author Prerit
 *
 */
public class CustomLogger {
	
	public static void info(String message){
		Reporter.log("<p style='color:blue;'>"+message+"</p>");
	}
	
	public static void error(String message){
		Reporter.log("<p style='color:red;'>"+message+"</p>");
	}
}
