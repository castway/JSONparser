package com.tomankiewicz.rafal.JSONparser;

import java.lang.reflect.Type;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Main application class  containing the main method. 
 * Responsible for creating all objects and invoking the run() method on a Runner instance.
 * 
 * @author Rafał
 *
 */
class Main {

	private static final String URL = "https://jsonplaceholder.typicode.com/posts";
	
	public static void main(String[] args) {
		
		// Gson object to be used for serialization & deserialization
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		// Type object to be used for JSON deserialization
		Type type = new TypeToken<Collection<Post>>(){}.getType();
		
		// OkHttp client object to be used for API calls
		OkHttpClient client = new OkHttpClient();
		
		// Request object to be sent by client object
		Request request = new Request.Builder().url(URL).build();
		
		// Main application object handling the business logic
		Runner runner = new Runner(new HttpRequestor(client, request), new JsonHandler(gson, type));
		runner.run();
	}
}
