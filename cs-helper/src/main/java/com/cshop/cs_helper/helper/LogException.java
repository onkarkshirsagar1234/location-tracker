package com.cshop.cs_helper.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cshop.cs_helper.constants.CommonConstants;

public class LogException {

	private static final Logger LOG = LogManager.getLogger();

	public static final String EXCEPTION_STRING_START = "############EXCEPTION START###########";
	public static final String EXCEPTION_STRING_END = "##########EXCEPTION END###########";
	public static final String EXCEPTION_DETAILS = "EXCEPTION DETAILS: ";
	public static final String CAUSE = "Cause: ";
	public static final String LOCALISED_MSG = "LocalizedMsg: ";
	public static final String MSG = "Message: ";
	public static final String STACKSTRACE = "Complete StackTrace: ";
	public static final String NEWLINE = "\n";
	public static final String CLASSNAME = "ClassName: ";
	public static final String METHODNAME = "MethodName: ";
	public static final String LINENUMBER = "LineNumber: ";

	/**
	 * This method is used to log the exception details.
	 * 
	 * @param e
	 * @param logId
	 */
	public static String logException(Exception e, String requestID) {
		String cause = CommonConstants.BLANK;
		LOG.error(requestID + EXCEPTION_STRING_START);
		LOG.error(requestID + EXCEPTION_DETAILS);
		LOG.error(requestID + CLASSNAME + e.getStackTrace()[0].getClassName());
		LOG.error(requestID + METHODNAME + e.getStackTrace()[0].getMethodName() + CommonConstants.BLANK_SPACE
				+ CommonConstants.DELIM_HASH + LINENUMBER + e.getStackTrace()[0].getLineNumber());
		if (e.getCause() != null) {
			cause = e.getCause().getLocalizedMessage();
			if (cause.trim().equalsIgnoreCase(CommonConstants.DATA_TRUNCATION)) {
				cause = cause + e.getCause().toString().split(CommonConstants.COLON)[2];
			}
			LOG.error(requestID + CAUSE + e.getCause());
		}
		if (e.getMessage() != null) {
			LOG.error(requestID + MSG);
			String[] newLIne = e.getMessage().split(NEWLINE);
			for (int i = 0; i < newLIne.length; i++) {
				LOG.error(requestID + newLIne[i]);
			}
			newLIne = null;
		}
		LOG.error(requestID + STACKSTRACE);

		StackTraceElement[] stackTrace = e.getStackTrace();
		for (int i = 0; i < stackTrace.length; i++) {
			LOG.error(requestID + stackTrace[i]);
		}
		stackTrace = null;
		LOG.error(requestID + EXCEPTION_STRING_END);
		return cause;
	}

}
