package com.tomankiewicz.rafal.JSONparser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
/**
 * Class responsible for handling serialization and deserialization of JSON.
 * 
 * @author Rafał
 *
 */
class JsonHandler {

	private final Gson gson;
	private final Type type;
	
	/**
	 * Constructor for JsonHandler class.
	 * @param gson an instance of the Gson class responsible for serializing / deserializing JSON format
	 * @param type an instance of the java.lang.reflect.Type class denoting the target type of JSON deserialization
	 */
	JsonHandler(Gson gson, Type type) {
		
		this.gson = gson;
		this.type = type;
	}
	
	/**
	 * Method deserializing JSON input to a list of model objects.
	 * @param input a String representation of JSON to be deserialized
	 * @return a list of objects deserialized from the input String
	 */
	List<Post> fromJson(String input){
		
		return gson.fromJson(input, type);
	}
	
	/**
	 * Method serializing a model object to JSON and saving it into a JSON file,
	 * using the object's id as a filename, i.e. <object_id>.json.
	 * The file is saved in the current directory (./).
	 * 
	 * @param post the instance of the model class to be saved into the JSON file
	 */
	void toFile(Post post) {
		
		String filename = String.valueOf(post.getId()) + ".json";
		
		try (Writer writer = new FileWriter(filename)) {
			gson.toJson(post, writer);
			writer.flush();
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
