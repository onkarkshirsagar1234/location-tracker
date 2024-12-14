package com.cshop.cs_helper.helper;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonHelper {
	static Gson gson = null;
	static Gson gsonDisHtmlEscap = null;

	/**
	 * This method is used to get object of Gson
	 * 
	 * @return an object of Gson
	 */
	public static Gson getGsonObj() {
		if (gson == null) {
			gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer())
					.registerTypeAdapter(Integer.class, new IntTypeAdapter())
					.registerTypeAdapter(Double.class, new DoubleTypeAdapter()).create();

		}
		return gson;
	}

	/**
	 * This method is used to get object of Gson with disable escapes HTML
	 * characters
	 * 
	 * @return an object of Gson
	 */
	public static Gson getGsonObjDisableHtmlEscaping() {

		if (gsonDisHtmlEscap == null) {
			gsonDisHtmlEscap = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer())
					.disableHtmlEscaping().registerTypeAdapter(Integer.class, new IntTypeAdapter())
					.registerTypeAdapter(Double.class, new DoubleTypeAdapter()).create();
		}
		return gsonDisHtmlEscap;
	}

}
