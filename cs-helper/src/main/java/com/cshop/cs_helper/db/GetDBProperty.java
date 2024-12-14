package com.cshop.cs_helper.db;

import java.io.BufferedReader;
import java.io.FileReader;

import com.cshop.cs_helper.bean.DBConnectProperties;
import com.cshop.cs_helper.constants.CommonConstants;
import com.cshop.cs_helper.helper.LogException;

public class GetDBProperty {

	public static DBConnectProperties getDatabaseProperties(String filePath, String requestID) {
		DBConnectProperties dbConnectProperties = new DBConnectProperties();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] keyValue = line.split(":");
				if (keyValue.length == 2) {
					String key = keyValue[0].trim();
					String value = keyValue[1].trim();
					setBeanProperty(dbConnectProperties, key, value);
				}
			}
		} catch (Exception e) {
			LogException.logException(e, requestID);
		}

		return dbConnectProperties;
	}

	private static void setBeanProperty(DBConnectProperties dbConnectProperties, String key, String value) {
		switch (key) {
		case CommonConstants.DB_URL: {
			dbConnectProperties.setDbUrl(value);
			break;
		}
		case CommonConstants.DB_USERNAME: {
			dbConnectProperties.setDbUserName(value);
			break;
		}
		case CommonConstants.DB_PASSWORD: {
			dbConnectProperties.setDbUserName(value);
			break;
		}
		case CommonConstants.DB_DRIVER_NAME: {
			dbConnectProperties.setDbUserName(value);
			break;
		}
		case CommonConstants.DB_PLATFORM: {
			dbConnectProperties.setDbUserName(value);
			break;
		}
		case CommonConstants.DB_DDL: {
			dbConnectProperties.setDbUserName(value);
			break;
		}
		case CommonConstants.DB_SHOW_SQL: {
			dbConnectProperties.setDbUserName(value);
			break;
		}

		default:
			throw new IllegalArgumentException("Unexpected value: " + key);
		}

	}
}
