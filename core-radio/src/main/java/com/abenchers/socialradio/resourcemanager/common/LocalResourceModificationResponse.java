package com.abenchers.socialradio.resourcemanager.common;

import java.util.List;

import com.abenchers.socialradio.common.BaseResponse;

public class LocalResourceModificationResponse extends BaseResponse {

	private List<String> resourceList;

	public List<String> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<String> resourceList) {
		this.resourceList = resourceList;
	}

}
