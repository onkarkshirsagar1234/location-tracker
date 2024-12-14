package com.cshop.cs_helper.bean;

import java.io.Serializable;

public class DBConnectProperties implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3336414361791420790L;

	public DBConnectProperties() {

	}

	private String driverName;
	private String dbUrl;
	private String dbUserName;
	private String dbPassword;
	private String sqlDialect;
	private String showSql;
	private String currentSessionContxtClass;
	private String defaultBatchFetchSize;
	private String dbenv;
	private String useSSL;
	private String dbType;

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public String getDbUserName() {
		return dbUserName;
	}

	public void setDbUserName(String dbUserName) {
		this.dbUserName = dbUserName;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getSqlDialect() {
		return sqlDialect;
	}

	public void setSqlDialect(String sqlDialect) {
		this.sqlDialect = sqlDialect;
	}

	public String getShowSql() {
		return showSql;
	}

	public void setShowSql(String showSql) {
		this.showSql = showSql;
	}

	public String getCurrentSessionContxtClass() {
		return currentSessionContxtClass;
	}

	public void setCurrentSessionContxtClass(String currentSessionContxtClass) {
		this.currentSessionContxtClass = currentSessionContxtClass;
	}

	public String getDefaultBatchFetchSize() {
		return defaultBatchFetchSize;
	}

	public void setDefaultBatchFetchSize(String defaultBatchFetchSize) {
		this.defaultBatchFetchSize = defaultBatchFetchSize;
	}

	public String getDbenv() {
		return dbenv;
	}

	public void setDbenv(String dbenv) {
		this.dbenv = dbenv;
	}

	public String getUseSSL() {
		return useSSL;
	}

	public void setUseSSL(String useSSL) {
		this.useSSL = useSSL;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

}
