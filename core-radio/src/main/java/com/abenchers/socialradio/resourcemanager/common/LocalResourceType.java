package com.abenchers.socialradio.resourcemanager.common;

public enum LocalResourceType {
	FILE("file"), DIRECTORY("directory");

	private String code;

	LocalResourceType(final String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

}
