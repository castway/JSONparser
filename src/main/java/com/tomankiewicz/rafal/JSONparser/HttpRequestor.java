package com.tomankiewicz.rafal.JSONparser;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * Class responsible for receiving and pre-processing response from the REST API.
 * 
 * @author Rafał
 *
 */
class HttpRequestor {

	private final OkHttpClient client;
	private final Request request;
	
	/**
	 * Constructor for HttpRequestor class.
	 * @param client an instance of OKHttpClient class
	 * @param request an instance of the Request class with the URL of the REST API
	 */
	HttpRequestor(OkHttpClient client, Request request) {
		this.client = client;
		this.request = request;
	}
	
	/**
	 * Method sending request to the REST API and processing it to a form of a String.
	 * @return the received JSON response in the form of a String
	 */
	String getResponse() {
		
		String json = null;
		
		try (ResponseBody response = client.newCall(request).execute().body()){
			
			json = response.string();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException("Problem obtaining JSON from REST API, files not created");
		}
		
		if (json == null || json.length() <= 2) {
			throw new RuntimeException("Empty JSON obtained from REST API, files not created");
		}
		
		return json;
	}
}
