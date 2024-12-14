package com.user.beans;

public class Response {

	private String responseMsg; // Response message
	private int responseCode; // Response code (e.g., HTTP status code)
	private String responseType; // Type of response (e.g., SUCCESS, ERROR)

	// Constructors
	public Response() {
	}

	public Response(String responseMsg, int responseCode, String responseType) {
		this.responseMsg = responseMsg;
		this.responseCode = responseCode;
		this.responseType = responseType;
	}

	// Getters and Setters
	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseType() {
		return responseType;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

}
