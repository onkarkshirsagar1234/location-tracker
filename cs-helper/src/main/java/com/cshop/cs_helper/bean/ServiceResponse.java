package com.cshop.cs_helper.bean;

import java.util.List;

public class ServiceResponse<T> {

	public static final String TYPE_SUCCESS = "S";
	public static final String TYPE_ERROR = "E";
	public static final String TYPE_WARNING = "W";
	public static final String TYPE_INFO = "I";
	public static final String FAILURE = "1111";
	public static final String SUCCESS = "0000";
	private List<T> Data;
	private String message;
	private String error;
	private String timestamp;
	private String status;
	private String requestID;

	public List<T> getData() {
		return Data;
	}

	public void setData(List<T> data) {
		Data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

}
