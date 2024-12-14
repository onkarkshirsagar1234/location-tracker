package com.cshop.cs_helper.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeHelper {

	public static final SimpleDateFormat TIME_STAMP_FORMAT = new SimpleDateFormat("yyyyMMddhhmmsss");
	public static final SimpleDateFormat CURRENT_DATE_FORMAT = new SimpleDateFormat("YYYYMMdd");
	public static final SimpleDateFormat CURRENT_TIME_FORMAT = new SimpleDateFormat("hhmmss");

	public static long getCurrentTimeStamp() {
		return Long.parseLong(TIME_STAMP_FORMAT.format(new Date()));
	}

	public static long getCurrentDate() {
		return Long.parseLong(CURRENT_DATE_FORMAT.format(new Date()));

	}

	public static long getCurrentTime() {
		return Long.parseLong(CURRENT_TIME_FORMAT.format(new Date()));
	}

	public static String getCurrentTimestampAsString() {
		String res = "";

		String pattern = "yyyy-MM-dd HH:mm:ss";

		// Create an instance of SimpleDateFormat used for formatting
		// the string representation of date according to the chosen pattern
		DateFormat df = new SimpleDateFormat(pattern);

		// Get the today date using Calendar object.
		Date today = Calendar.getInstance().getTime();
		// Using DateFormat format method we can create a string
		// representation of a date with the defined format.
		res = df.format(today);

		return res;
	}

}
