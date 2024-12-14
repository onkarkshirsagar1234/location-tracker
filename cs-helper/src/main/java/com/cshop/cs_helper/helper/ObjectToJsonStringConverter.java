package com.cshop.cs_helper.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cshop.cs_helper.constants.MessageConstants;

public class ObjectToJsonStringConverter {
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * This method is used to convert object to json string
	 * 
	 * @param inputObj  an object which need to convert to json String
	 * @param requestId an requestId used to trace service call
	 * @return an json string
	 */
	public static String ObjectToJson(Object inputObj, String requestId) {
		String object = null;
		try {
			if (inputObj != null) {
				object = GsonHelper.getGsonObj().toJson(inputObj);
				LOG.info(requestId + MessageConstants.OBJ_CONVERTED_JSON);
			}
		} catch (Exception e) {
			LOG.info(requestId + MessageConstants.OBJ_CONVERTED_JSON_FAIL);
			LogException.logException(e, requestId);
		}
		return object;
	}

	/**
	 * This method is used to convert object to json string with disable escapes
	 * HTML characters
	 * 
	 * @param inputObj  an object which need to convert to json String
	 * @param requestId an requestId used to trace service call
	 * @return an json string
	 */
	public static String ObjectToJsonDisableHtmlEscaping(Object inputObj, String requestId) {
		String object = null;
		try {
			if (inputObj != null) {
				object = GsonHelper.getGsonObjDisableHtmlEscaping().toJson(inputObj);
				LOG.info(requestId + MessageConstants.OBJ_CONVERTED_JSON);
			}
		} catch (Exception e) {
			LOG.info(requestId + MessageConstants.OBJ_CONVERTED_JSON_FAIL);
			LogException.logException(e, requestId);
		}
		return object;
	}
}
