package com.user.controller;

import java.util.random.RandomGenerator;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.user.Helper.RandomNumberGenerator;
import com.user.beans.UserRequest;
import com.user.beans.UserResponse;
import com.user.userBD.UserRegisterBD;
import com.user.userBD.UserRegisterBDImpl;

@RestController("/login")
public class UserLogin {

	Gson gson = new Gson();

	String LOgID = RandomNumberGenerator.generateLogID();

	@PostMapping("/userRegister")
	UserResponse userRegister(@RequestBody String inputJson) {
		UserResponse userResponse = new UserResponse();

		UserRequest userRequest = gson.fromJson(inputJson, UserRequest.class);
		if (userRequest != null) {
			UserRegisterBD userRegisterBD = new UserRegisterBDImpl();
			userResponse = userRegisterBD.userRegister(userRequest, LOgID);
		} else {

		}
		return userResponse;
	}

}
