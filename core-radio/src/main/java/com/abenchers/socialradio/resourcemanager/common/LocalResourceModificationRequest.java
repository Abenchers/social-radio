package com.abenchers.socialradio.resourcemanager.common;

import com.abenchers.socialradio.common.BaseRequest;

public class LocalResourceModificationRequest extends BaseRequest {

	private String resource;

	private LocalResourceType localResourceType;

	private LocalResourceAction action;

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public LocalResourceType getLocalResourceType() {
		return localResourceType;
	}

	public void setLocalResourceType(LocalResourceType localResourceType) {
		this.localResourceType = localResourceType;
	}

	public LocalResourceAction getAction() {
		return action;
	}

	public void setAction(LocalResourceAction action) {
		this.action = action;
	}

}
