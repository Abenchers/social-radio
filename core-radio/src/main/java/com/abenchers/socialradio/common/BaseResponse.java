package com.abenchers.socialradio.common;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BaseResponse {

	private ResponseStatus status;

	private String description;

	public ResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
