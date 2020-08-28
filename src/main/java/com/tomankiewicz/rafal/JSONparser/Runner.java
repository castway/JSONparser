package com.tomankiewicz.rafal.JSONparser;

import java.util.List;

/**
 * Class responsible for the application's business logic.
 * 
 * @author Rafał
 *
 */
class Runner {

	private final HttpRequestor httpRequestor;
	private final JsonHandler jsonHandler;
	
	/**
	 * Constructor for Runner class
	 * @param httpRequestor an instance of HttpRequestor class
	 * @param jsonHandler an instance of JsonHandler class
	 */
	Runner(HttpRequestor httpRequestor, JsonHandler jsonHandler){
		this.httpRequestor = httpRequestor;
		this.jsonHandler = jsonHandler;
	}
	
	/**
	 * Method responsible for the application's business logic.
	 */
	void run() {
		
		String json = httpRequestor.getResponse();
		
		List<Post> objectList = jsonHandler.fromJson(json);
		
		for (Post post : objectList) {
			jsonHandler.toFile(post);
		}
		
		System.exit(0);
	}
}
