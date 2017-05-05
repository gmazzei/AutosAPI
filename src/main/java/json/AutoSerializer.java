package main.java.json;

import java.lang.reflect.Type;

import main.java.domain.Auto;
import main.java.domain.Opcional;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class AutoSerializer implements JsonSerializer<Auto> {

	@Override
	public JsonElement serialize(Auto auto, Type type, JsonSerializationContext context) {
		JsonObject jsonObject = new JsonObject();
		
		jsonObject.addProperty("id", auto.getId());
		jsonObject.addProperty("nombre", auto.getTipo().getNombre());
		jsonObject.addProperty("precio", auto.getPrecio());
		
		JsonArray listaDeOpcionales = new JsonArray();
		for (Opcional opcional : auto.getOpcionales()) {
			listaDeOpcionales.add(opcional.getSigla());
		}
		
		jsonObject.add("opcionales", listaDeOpcionales);
		
		return jsonObject;
	}
	
	
	
	
}