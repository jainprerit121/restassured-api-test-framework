package com.gojeck.api.assignment.unit.test;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.gojeck.api.assignment.utils.RestAssuredAPIUtils;

import io.restassured.response.Response;


public class RestAssuredAPIUtilsTest
{
	@Test
	public void testGetWithInvalidURL()
	{
		RestAssuredAPIUtils restUtil =new RestAssuredAPIUtils();
		Map<String, String> headers = new HashMap<>();
		Response response= restUtil.GET(headers, "");
		Assert.assertTrue( response==null );
	}

	@Test
	public void testGetWithValidURL()
	{
		RestAssuredAPIUtils restUtil =new RestAssuredAPIUtils();
		Map<String, String> headers = new HashMap<>();
		Response response= restUtil.GET(headers, "https://reqres.in/api/users/3");
		Assert.assertTrue( response.getStatusCode()==200 );
	}
}
