package com.cshop.cs_helper.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cshop.cs_helper.constants.CommonConstants;
import com.cshop.cs_helper.constants.MessageConstants;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class IDHelper {

	private static final Logger LOG = LogManager.getLogger();
	static JsonParser parser = new JsonParser();

	/**
	 * This method is used to get the request ID from the json string
	 * 
	 * @param jsonString an input json String
	 * @return requestID
	 */
	public static String getIDFromInput(String jsonString) {

		long startTime = System.currentTimeMillis();
		LOG.info(MessageConstants.GET_REQUEST_ID + MessageConstants.START);
		String requestID = CommonConstants.BLANK;
		if (jsonString.startsWith(CommonConstants.OPENING_BRACE)) {
			final JsonElement jsonElement = parser.parse(jsonString);
			if (jsonElement.isJsonObject()) {
				JsonObject jsonObject = jsonElement.getAsJsonObject();
				if (jsonObject.get(CommonConstants.REQUESTID) != null) {
					requestID = jsonObject.get(CommonConstants.REQUESTID).toString();
					/* REMOVE DOUBLE QUOTE FROM STRING REQUEST ID */
					requestID = requestID.replace(CommonConstants.DOUBLE_QUOTE, CommonConstants.BLANK);
				}

			}
		} else {
			requestID = JAXBHelper.getTagValue(jsonString, CommonConstants.REQUESTID);
		}
		long endTime = System.currentTimeMillis();
		LOG.info(MessageConstants.EXEC_TIME + (endTime - startTime));
		LOG.info(requestID + MessageConstants.NEW_REQUEST);
		LOG.info(requestID + MessageConstants.INPUT_SEPARATOR);
		return requestID;

	}
}
