package com.gojeck.api.assignment.unit.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.gojeck.api.assignment.utils.JSONComparator;

public class JSONComparatorTest {

	private String json1 = "{\"id\": 1,\"email\":\"emma.wong@reqres.in\",\"first_name\":\"Emma\"}";
	private String json2 = "{\"id\": 2,\"email\":\"emma.wong@reqres.in\",\"first_name\":\"Emma\"}";;
	private String json3SameAsJson1 = "{\"id\": 1,\"email\":\"emma.wong@reqres.in\",\"first_name\":\"Emma\"}";

	@Test
	public void testDistinctJSONCompare(){
		JSONComparator comparator = new JSONComparator(json1, json2);
		Assert.assertFalse(comparator.compare());
	}

	@Test
	public void testSameJSONCompare(){
		JSONComparator comparator = new JSONComparator(json1, json3SameAsJson1);
		Assert.assertTrue(comparator.compare());
	}
}
