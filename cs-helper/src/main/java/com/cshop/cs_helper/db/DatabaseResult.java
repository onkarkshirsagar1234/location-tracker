package com.cshop.cs_helper.db;

import java.util.List;

public class DatabaseResult {

	private String status;
	private int noRowUpdated;
	private int noRowGet;
	private List list;
	private String exceptionStr;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getNoRowUpdated() {
		return noRowUpdated;
	}

	public void setNoRowUpdated(int noRowUpdated) {
		this.noRowUpdated = noRowUpdated;
	}

	public int getNoRowGet() {
		return noRowGet;
	}

	public void setNoRowGet(int noRowGet) {
		this.noRowGet = noRowGet;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getExceptionStr() {
		return exceptionStr;
	}

	public void setExceptionStr(String exceptionStr) {
		this.exceptionStr = exceptionStr;
	}

}
