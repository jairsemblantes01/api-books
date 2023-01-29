package com.distribuida.db;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

import java.util.List;

public class EntityUtils {

public static JsonObject toJsonObject(Book book) {
	return Json.createObjectBuilder()
					       .add("id", book.getId())
					       .add("title", book.getTitle())
					       .add("isbn", book.getIsbn())
					       .add("author", book.getAuthor())
					       .build();
}
public static JsonArray toJsonArray(List<Book> book) {
	JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
	book.forEach(p -> {
		jsonArrayBuilder.add(toJsonObject(p));
	});
	
	return jsonArrayBuilder.build();
}

}
