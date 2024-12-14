package com.user.beans;

public class Errors {

    private int errorCode;      // Error code (e.g., 400, 404, etc.)
    private String errorMsg;    // Error message describing the issue
    private String errorType;   // Error type (e.g., VALIDATION_ERROR, DATABASE_ERROR)

    // Constructors
  
    // Getters and Setters
    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }
}


