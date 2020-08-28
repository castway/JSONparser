package com.tomankiewicz.rafal.JSONparser;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

class JsonHandlerTest {

	private static String JSON;
	
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	private static final Type type = new TypeToken<Collection<Post>>(){}.getType();
	
	private static Post post1;
	
	private static Post post2;
	
	@BeforeAll
	static void createJsonString() {
		
		post1 = new Post();
		post2 = new Post();
		
		post1.setUserId(1);
		post1.setId(10);
		post1.setTitle("Title1");
		post1.setBody("Body1");

		post2.setUserId(2);
		post2.setId(20);
		post2.setTitle("Title2");
		post2.setBody("Body2");
		
		Post[] array = {post1, post2};
		JSON = gson.toJson(array);
	}
	
	@Test
	void jsonParsingMethodShouldConvertJsonToAlistOfObjects() {
		
		//given
		JsonHandler jsonHandler = new JsonHandler(gson, type);
		
		//when
		List<Post> list = jsonHandler.fromJson(JSON);
		
		//then
		assertTrue(list.size() == 2);
		assertTrue(list.get(0) instanceof Post && list.get(1) instanceof Post);
		assertEquals(post1, list.get(0));
		assertEquals(post2, list.get(1));
	}
	
	@Test
	void toFileMethodShouldCreateNewFileIfFileNotExisting() throws FileNotFoundException {
		
		//given
		JsonHandler jsonHandler = new JsonHandler(gson, type);
		File file = new File("10.json");
		

		//when
		jsonHandler.toFile(post1);
		
		//then
		assertTrue(file.exists());
		
		//check for file contents
		Scanner reader = new Scanner(file);
	
		String fileContent = "";
		
		while (reader.hasNextLine()) {
			fileContent += reader.nextLine();
		}
		
		assertTrue(fileContent.contains("Title1"));
		assertTrue(fileContent.contains("Body1"));
		reader.close();
	}
	
	@Test
	void toFileMethodShouldOverwriteFileIfFileExists() throws IOException {
		
		//given 
		JsonHandler jsonHandler = new JsonHandler(gson, type);
		File file = new File("20.json");
		Writer writer = new FileWriter(file);
		writer.write("test");
		
		//when
		jsonHandler.toFile(post2);
		
		//then
		//check for file contents
		Scanner reader = new Scanner(file);
			
		String fileContent = "";
				
		while (reader.hasNextLine()) {
			fileContent += reader.nextLine();
		}
				
		assertTrue(fileContent.contains("Title2"));
		assertTrue(fileContent.contains("Body2"));
		assertFalse(fileContent.contains("test"));
		writer.close();
		reader.close();
		
	}

}
