package com.cshop.cs_helper.db;

public class DBProperties {

	private String driverClassName;
	private String url;
	private String username;
	private String password;
	private String hibernateDialect;
	private String showSql;
	private String hbmtoddl;

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHibernateDialect() {
		return hibernateDialect;
	}

	public void setHibernateDialect(String hibernateDialect) {
		this.hibernateDialect = hibernateDialect;
	}

	public String getShowSql() {
		return showSql;
	}

	public void setShowSql(String showSql) {
		this.showSql = showSql;
	}

	public String getHbmtoddl() {
		return hbmtoddl;
	}

	public void setHbmtoddl(String hbmtoddl) {
		this.hbmtoddl = hbmtoddl;
	}

}
