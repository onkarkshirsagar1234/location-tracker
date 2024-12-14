package com.cshop.cs_helper.helper;

import java.lang.reflect.Type;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cshop.cs_helper.constants.CommonConstants;
import com.cshop.cs_helper.constants.MessageConstants;

public class JsonStringToObjectConverter {

	private static final Logger LOG = LogManager.getLogger();

	/**
	 * This method is sued to convert input json to object
	 * 
	 * @param input an input string
	 * @param logId an logId
	 * @param clas  an Class
	 * @param type  an object of type
	 * @return object
	 */
	public static Object jsonStringToObject(String input, Class<?> clas, Type type, String requestID) {
		long startTime = System.currentTimeMillis();
		Object bean = null;

		try {

			if (input != null) {
				/* check inputType JSON or String */
				/* if input string start with { it will JSON */
				if (input.startsWith(CommonConstants.OPENING_BRACE)) {
					/* Input is in JSON Format */
					bean = GsonHelper.getGsonObj().fromJson(input, type);
				} else {
					/* Input is in XML format */
					/* USE JAXB PARSER */
					bean = JAXBHelper.unmarshal(input, clas);
				}

			} else {
				bean = null;
				LOG.info(requestID + MessageConstants.INPUT_STRING_CONVERSION_OBJECT_FAIL);
			}
		} catch (Exception e) {
			LogException.logException(e, requestID);
		}
		long endTime = System.currentTimeMillis();
		return bean;

	}

	/**
	 * This method is used to convert json string to object
	 * 
	 * @param input
	 * @param requestID
	 * @param c
	 * @param type
	 * @return
	 */
	public static Object[] JSonStringToObjectWithException(String input, String requestID, Class<?> c, Type type) {
		long startTime = System.currentTimeMillis();
		Object[] bean = new Object[2];
		try {
			if (input != null && !input.isEmpty()) {
				if (input.startsWith(CommonConstants.OPENING_BRACE)) {
					bean[0] = GsonHelper.getGsonObj().fromJson(input, type);
				} else {
					/* USE JAXB PARSER */
					bean[0] = JAXBHelper.unmarshal(input, c);
				}
			}
		} catch (Exception e) {
			bean[1] = LogException.logException(e, requestID);
		}
		long endTime = System.currentTimeMillis();
		return bean;
	}
}
