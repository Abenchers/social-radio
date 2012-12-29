package com.abenchers.socialradio.usermanager;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abenchers.socialradio.usermanager.common.UserManagerRequest;
import com.abenchers.socialradio.usermanager.common.UserManagerResponse;
import com.abenchers.socialradio.usermanager.service.UserManagerService;

@Component("userManagerRestService")
public class UserManagerRestService {

	@Autowired
	private UserManagerService userManagerService;

	@Path("/start")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public UserManagerResponse startUser(UserManagerRequest request){
		return userManagerService.startUser(request);
	}
}
