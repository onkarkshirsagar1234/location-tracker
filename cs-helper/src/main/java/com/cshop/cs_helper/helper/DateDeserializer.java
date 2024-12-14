package com.cshop.cs_helper.helper;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

public class DateDeserializer implements JsonDeserializer<Date> {

	SimpleDateFormat smDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
	DateTimeFormatter dtTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");
	public String T = "T";

	public Date deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		String date = element.getAsString();
		if (date != null && !date.isEmpty()) {
			try {
				if (date.contains(T)) {
					ZonedDateTime d = ZonedDateTime.parse(date);
					return smDateFormat.parse(dtTimeFormat.format(d));
				} else {
					return smDateFormat.parse(date);
				}
			} catch (Exception e) {
				System.out.println("DATE: " + date);
				e.printStackTrace();
				return null;
			}

		} else {
			return null;
		}

	}

	public static void main(String[] args) {
		try {
			String date = "2020-05-11 15:59:08 +0800";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");
			ZonedDateTime zonedDateTime = ZonedDateTime.parse(date, formatter);
			System.out.println(formatter.format(zonedDateTime));

			DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");
			String date1 = "2020-05-18T23:59:59+08:00";
			ZonedDateTime d = ZonedDateTime.parse(date1);

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
			System.out.println(formatter.format(d));
			Date parse = format.parse(formatter.format(d));
			System.out.println(parse);

			ZonedDateTime zonedDateTime1 = ZonedDateTime.parse(date, formatter);
			System.out.println(formatter.format(zonedDateTime1));

			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");

			Date parse1 = format.parse(date);
			System.out.println(parse1);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

}
