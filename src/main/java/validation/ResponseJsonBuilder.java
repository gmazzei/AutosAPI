package main.java.validation;

import com.google.gson.JsonObject;

public class ResponseJsonBuilder {
	
	public static String build(Integer code, String message) {
		JsonObject json = new JsonObject();
		json.addProperty("code", code);
		json.addProperty("message", message);
		return json.toString();
	}
	
}
