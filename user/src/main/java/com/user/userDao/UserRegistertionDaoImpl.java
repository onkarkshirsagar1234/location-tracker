package com.user.userDao;

import com.cshop.cs_helper.db.DBHelper;
import com.cshop.cs_helper.db.DatabaseResult;
import com.user.beans.UserRequest;

public class UserRegistertionDaoImpl {

	public boolean isUserExitsInDB(String userEmailID, String logID) {
		boolean isUserExit = false;
		// TODO Auto-generated method stub
		String sqlQuesry = "select * from User where UserEmailID = " + userEmailID;
		DatabaseResult dbResult = DBHelper.executeSqlQuery(sqlQuesry, "get records from User Table", logID);
		if (dbResult == null) {
			System.out.println(logID + ":: user not Exits !!");
			isUserExit = false;

		} else {
			System.out.println(logID + ":: User IS Not Exits in DB!!");
			isUserExit = true;
		}
		return isUserExit;

	}

	public boolean registerUser(UserRequest userRequest, String logID) {
		boolean userSave = false;
		
		DatabaseResult databaseResult = DBHelper.saveOrUpdate(userRequest, "User Registeration", logID);
		if (databaseResult != null) {
			System.out.println(logID + ":: User Registration Successfull !!!");
			userSave = true ;
		} else {
			System.out.println(logID + ":: User Registration Failed !!!");
			userSave= false;
		}
		return userSave;
	}

}
