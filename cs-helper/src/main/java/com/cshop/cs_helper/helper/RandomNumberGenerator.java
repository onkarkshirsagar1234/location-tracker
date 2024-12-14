package com.cshop.cs_helper.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomNumberGenerator {

	private static final int DEF_COUNT = 7;

	public static String generateRandomNumberForReqID() {

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String timestamp = now.format(dtf);
		String randomnumeric = RandomStringUtils.randomNumeric(DEF_COUNT);

		return "CS" + timestamp + "_" + randomnumeric;
	}
}
