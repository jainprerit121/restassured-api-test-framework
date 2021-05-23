package com.gojeck.api.assignment.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import io.restassured.RestAssured;
import io.restassured.config.ConnectionConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;

/**
 * @author Prerit Jain
 *
 */
public class RestAssuredAPIUtils {

	/**
	 * Returns response of GET API method execution based on provided query parameters
	 * @param url: End Point of the API
	 * @param headers: Request headers
	 * @return Response
	 * @throws MalformedURLException 
	 */
	public Response GET(Map<String, String> headers, String url) 
	{
//		if(StringUtils.isEmpty(url)){
//			CustomLogger.error("URL cannot be null or empty");
//			return null;
//		}
		
		try{
			Response resp = RestAssured.given()
					.headers(headers)
					.get(new URL(url));
			CustomLogger.info("Running GET command");		
			CustomLogger.info("Status Code: \n" + resp.getStatusCode()+"\n");		
			CustomLogger.info("Time taken to get response is: \n" + resp.getTime()+" milli second\n");
			return resp;
		}catch(MalformedURLException e){
			CustomLogger.error("Invalid URL "+url);
			return null;
		}
	}

	/**
	 * Returns response of POST API method execution: This is just for example 
	 * if we want to continue the same framework we can implement such methods 
	 * for other type of requests like PUT/DELETE etc.
	 * 
	 * @param headers
	 * @param body
	 * @param url
	 * @return Response of POST command
	 * @throws MalformedURLException 
	 */
	public Response POST(String baseURL,Map<String, String> headers, String body, String url) {
		try{
			Response resp = RestAssured.given()
					.headers(headers)				
					.body(body).post(new URL(url))
					.andReturn();
			CustomLogger.info("Running POST command");
			CustomLogger.info(baseURL);
			CustomLogger.info("Status Code \n" + resp.getStatusCode());
			return resp;
		}catch(MalformedURLException e){
			CustomLogger.error("Invalid URL "+url);
			return null;
		}
	}

	/**
	 * 
	 * closeConnection method would be closing idle Connection
	 * 
	 */
	public void closeConnection()
	{
		RestAssured.config = RestAssuredConfig.newConfig().connectionConfig(ConnectionConfig.connectionConfig().closeIdleConnectionsAfterEachResponse());
	}

}