package com.gojeck.api.assignment.comparators;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gojeck.api.assignment.utils.CustomLogger;

/**
 * 
 * @author Prerit
 *
 */
public class JSONComparator {

	private String json1;
	private String json2;

	public JSONComparator(String json1, String json2) {
		this.json1 = json1; 
		this.json2 = json2;
	}

	public boolean compare()  {
		try{
			JsonFactory factory = new JsonFactory();

			ObjectMapper mapper = new ObjectMapper(factory);
			JsonNode rootNode1 = mapper.readTree(json1);  	       
			JsonNode rootNode2 = mapper.readTree(json2);  

			return rootNode1.equals(rootNode2);

		}catch(IOException e){
			CustomLogger.error("Error while parsing JSON"+ e.getMessage());
			return false;
		}
	}
}
