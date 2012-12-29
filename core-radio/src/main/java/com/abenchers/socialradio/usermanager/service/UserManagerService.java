package com.abenchers.socialradio.usermanager.service;

import com.abenchers.socialradio.usermanager.common.UserManagerRequest;
import com.abenchers.socialradio.usermanager.common.UserManagerResponse;

public interface UserManagerService {

	UserManagerResponse startUser(UserManagerRequest request);

}
