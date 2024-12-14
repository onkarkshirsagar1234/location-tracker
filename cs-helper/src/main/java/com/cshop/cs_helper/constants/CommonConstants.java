package com.cshop.cs_helper.constants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.message.Message;

import jakarta.persistence.EntityManager;

public class CommonConstants {

	public static final String EXEC_TIME = " Execution time in ms: ";
	public static final String EXCEPTION_STRING_START = "############EXCEPTION START###########";
	public static final String EXCEPTION_STRING_END = "##########EXCEPTION END###########";
	public static final String INPUT_START = "##INPUT START##";
	public static final String INPUT_END = "##INPUT END##";
	public static final String DATA_TRUNCATION = "Data truncation";
	public static final String SPACE = " ";
	public static final String SLASH = "/";
	public static final String OPENING_BRACE = "{";
	public static final String OPENING_BRACKET = "(";
	public static final String CLOSING_BRACKET = ")";
	public static final String CLOSING_BRACE = "} ";
	public static final String SINGLE_QUOTE = "'";
	public static final String JSON = "JSON";
	public static final String XML = "XML";
	public final static String DOUBLE_QUOTE = "\"";
	public static final String DOT = ".";
	public static final String DELIM_TILDE = "~";
	public static final String DELIM_HASH = "#";
	public static final String EQUALS_TO = "=";
	public static final String AMPERSAND = "&";
	public static final String QUETION_MARK = "?";
	public static final String BLANK = "";
	public static final String BLANK_SPACE = " ";
	public static final String NEW_LINE = "\n";
	public static final String COMMA = ",";
	public static final String REQUEST_HOST_IP = "Request coming from ip addr: ";
	public static final String INPUT_JSON = "INPUT_JSON ";
	public static final String COLON = ":";
	public static final String MYSQL_USER_NA = "NA";
	public static final String VERTICAL_BAR = "|";
	public static final String CONTENT_TYPE_JSON = "application/json";
	public static final String AUTH_PARAM = "Authorization";
	public static final String HMAC_SHA_256 = "HmacSHA256";
	public static final String ENCODE_UTF8 = "UTF-8";
	public static final String REQUESTID = "requestID";
	public static final String MIN_IMAGE_WIDTH = "minWidth";
	public static final String MIN_IMAGE_HEIGHT = "minHeight";
	public static final String MAX_IMAGE_WIDHT = "maxWidth";
	public static final String MAX_IMAGE_HEIGHT = "maxHeight";
	public static final String OPTIMAL_IMAGE_WIDTH = "optimalWidth";
	public static final String OPTIMAL_IMAGE_HEIGHT = "optimalHeight";
	public static final String HTTP_METHOD_GET = "GET";

	public static final String SUCCESS = "SUCCESS";
	public static final String FAIL = "FAIL";
	public static final String SELECT = "select";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";
	public static final String SELECT_C = "SELECT";
	public static final String UPDATE_C = "UPDATE";
	public static final String DELETE_C = "DELETE";
	public static final String START_INDEX = "START_INDEX";
	public static final String END_INDEX = "END_INDEX";
	public static final String RESTRICTION_NOT_IN = "!";
	public static final String ORDER_BY_DESC = "desc";
	public static final String ORDER_BY_ASC = "asc";
	public static final String RESTRICTION_GT = ">";
	public static final String RESTRICTION_LE = "<";
	public static final String DB_URL = "url";
	public static final String DB_USERNAME = "username";
	public static final String DB_PASSWORD = "password";
	public static final String DB_DRIVER_NAME = "driverName";
	public static final String DB_PLATFORM = "databasePlatform";
	public static final String DB_DDL = "ddl";
	public static final String DB_SHOW_SQL = "showSql";
	public static final String DATE_TIME_FORMAT = "dd:MM:yyyy HH:mm:ss";
	public static final String ERROR_WHILE_GETTING_DBDDATA = "USER OS EX";
	public static final String DAO = "DAO";
	public static final String DOUBLE_COULEN = "::";
	public static LocalDateTime LOCAL_DATE_TIME = null;
	public static DateTimeFormatter DATE_TIME_FORMATTER = null;
	public static final String START = "START";
}
