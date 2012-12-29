package com.abenchers.socialradio.common.restclient;

public class RestClientException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1098332019120115313L;

	private Exception exception;

	public RestClientException(Exception e) {
		this.exception = e;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

}
