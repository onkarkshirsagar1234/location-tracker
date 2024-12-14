package com.cshop.cs_helper.helper;

import java.io.IOException;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class DoubleTypeAdapter extends TypeAdapter<Double> {

	@Override
	public Double read(JsonReader in) throws IOException {
		if (in.peek() == JsonToken.NULL) {
			in.nextNull();
			return null;
		}
		try {
			String result = in.nextString();
			if ("".equals(result)) {
				return 0.0;
			}
			return Double.parseDouble(result);
		} catch (NumberFormatException e) {
			throw new JsonSyntaxException(e);
		}
	}

	@Override
	public void write(JsonWriter out, Double value) throws IOException {
		out.value(value);
	}

}