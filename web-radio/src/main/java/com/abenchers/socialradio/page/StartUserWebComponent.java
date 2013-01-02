package com.abenchers.socialradio.page;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abenchers.socialradio.usermanager.common.UserManagerRequest;
import com.abenchers.socialradio.usermanager.common.UserManagerResponse;
import com.abenchers.socialradio.usermanager.service.UserManagerService;

@ManagedBean(name = "startUserWebComponent")
@Component
@SessionScoped
public class StartUserWebComponent {

	@Autowired
	// @ManagedProperty(value="#{UserManagerService}")
	private UserManagerService userManagerService;

	private static final String SOCIAL_LIST = "socialList";

	public String streamingUrl() {

		UserManagerRequest userManagerRequest = new UserManagerRequest();
		userManagerRequest.setUserId(SOCIAL_LIST);
		UserManagerResponse userManagerResponse = userManagerService
				.startUser(userManagerRequest);
		return userManagerResponse.getStreamingURL();
	}

}
