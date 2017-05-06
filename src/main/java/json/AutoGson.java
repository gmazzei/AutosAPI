package main.java.json;

import main.java.domain.Auto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class AutoGson {
	
	public Gson create() {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Auto.class, new AutoSerializer());
		return builder.create();
	}
	
}
