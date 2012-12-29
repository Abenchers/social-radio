package com.abenchers.socialradio.resourcemanager.common;

public enum LocalResourceAction {

	ADD("add"), REMOVE("remove"), UPDATE("update"), LIST("list");

	private String code;

	LocalResourceAction(final String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}
}
