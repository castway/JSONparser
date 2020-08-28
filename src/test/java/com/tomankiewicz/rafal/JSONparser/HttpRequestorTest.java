package com.tomankiewicz.rafal.JSONparser;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpRequestorTest {

	private HttpRequestor req;
	
	@Test
	void getResponseMethodShouldThrowRuntimeExceptionIfUnableToObtainJson() {
		
		//given
		Request request = new Request.Builder().url("https://nonexistingurl.com").build();
		req = new HttpRequestor(new OkHttpClient(), request);
		
		//when
		//then
		assertThrows(RuntimeException.class, () -> req.getResponse());
		
	}
	
	@Test
	void getResponseMethodShouldThrowRuntimeExceptionIfEmptyJsonReturned() {
		
		//given
		Request request = new Request.Builder().url("https://jsonplaceholder.typicode.com/postssdgf").build();
		req = new HttpRequestor(new OkHttpClient(), request);
		
		//when
		//then
		assertThrows(RuntimeException.class, () -> req.getResponse());
		
	}
	
	@Test
	void getResponseMethodShouldReturnAValidStringIfResponseObtainedWithNoErrors() {
		
		//given
		Request request = new Request.Builder().url("https://jsonplaceholder.typicode.com/posts").build();
		req = new HttpRequestor(new OkHttpClient(), request);

		//when
		String response = req.getResponse();
		
		//then
		assertFalse(response.isEmpty());
		assertTrue(response.contains("userId"));
		assertTrue(response.contains("id"));
		assertTrue(response.contains("title"));
		assertTrue(response.contains("body"));
		
	}
	
		
}
