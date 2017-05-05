package main.java.utils;

import java.util.List;

import main.java.inteface.Jsonizable;

public class JsonUtils {
	
	public static String toJson(List<Jsonizable> list) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		
		for (Jsonizable element : list) {
			json.append(element.toJson());
		}
		
		json.append("]");
		
		return json.toString();
	}
	
}
