package com.user.userBD;

import com.user.beans.UserRequest;
import com.user.beans.UserResponse;

public interface UserRegisterBD {

	UserResponse userRegister(UserRequest userRequest, String lOgID);

}
