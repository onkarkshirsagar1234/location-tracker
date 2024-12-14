package com.user.beans;

import java.util.List;

public class UserResponse<T> {
	List<Object> data;
	Response respone;
	Errors error ;
	public List<Object> getData() {
		return data;
	}
	public void setData(List<Object> data) {
		this.data = data;
	}
	public Response getRespone() {
		return respone;
	}
	public void setRespone(Response respone) {
		this.respone = respone;
	}
	public Errors getError() {
		return error;
	}
	public void setError(Errors error) {
		this.error = error;
	}
	

}
