package com.abenchers.socialradio.common.restclient;

public enum MethodType {
	GET("get"), PUT("put"), DELETE("delete"), POST("post");

	private String code;

	MethodType(final String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

}
