package com.user.Helper;

import java.util.Random;

public class RandomNumberGenerator {

	public static String generateLogID() {
		Random random = new Random();

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(random.nextInt(10) + 1);

		for (int i = 0; i <= 11; i++) {
			stringBuilder.append(random.nextInt(11));
		}
		return stringBuilder.toString();

	}

}
