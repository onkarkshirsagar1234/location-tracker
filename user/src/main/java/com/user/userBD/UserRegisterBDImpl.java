package com.user.userBD;

import java.util.ArrayList;
import java.util.List;

import com.user.beans.Response;
import com.user.beans.UserRequest;
import com.user.beans.UserResponse;
import com.user.userDao.UserRegistertionDaoImpl;

public class UserRegisterBDImpl implements UserRegisterBD {

	UserRegistertionDaoImpl userDaoImpl = new UserRegistertionDaoImpl();

	@Override
	public UserResponse userRegister(UserRequest userRequest, String logID) {
		UserResponse userResponse = new UserResponse();
		List<UserRequest> list = new ArrayList();
		boolean userSave = false;
		Response response = new Response();
		boolean isUserExits = userValidation(userRequest.getUserEmailID(), logID);
		if (!isUserExits) {
			userSave = userDaoImpl.registerUser(userRequest, logID);
			if (userSave) {
				response.setResponseMsg("User Registration Succussfull !!!");
				response.setResponseCode(0000);
				response.setResponseType("SUCCESS");
			} else {
				response.setResponseMsg("User Registration Failed !!!");
				response.setResponseCode(1111);
				response.setResponseType("Failed");
			}
			list.add(userRequest);
			userResponse.setRespone(response);
			userResponse.setData(list);
		}
		return userResponse;
	}

	private boolean userValidation(String userEmailID, String logID) {
		boolean userExitsInDB = userDaoImpl.isUserExitsInDB(userEmailID, logID);

		return userExitsInDB;
	}

}
