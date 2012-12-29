package com.abenchers.socialradio.usermanager.common;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserManagerRequest {

	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
