package com.abenchers.socialradio.contentinformation.handler;

import com.abenchers.socialradio.common.BaseRequest;
import com.abenchers.socialradio.common.BaseResponse;
import com.abenchers.socialradio.contentinformation.exception.ContentInformationException;

public interface ContentInformationHandler<T extends BaseRequest, U extends BaseResponse> {

	U process (T request) throws ContentInformationException;
}
