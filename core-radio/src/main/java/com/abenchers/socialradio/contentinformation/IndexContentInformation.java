package com.abenchers.socialradio.contentinformation;

import com.abenchers.socialradio.common.ResponseStatus;
import com.abenchers.socialradio.common.Track;
import com.abenchers.socialradio.contentinformation.exception.ContentInformationException;

public interface IndexContentInformation {

 ResponseStatus index(Track track)
			throws ContentInformationException;

}
