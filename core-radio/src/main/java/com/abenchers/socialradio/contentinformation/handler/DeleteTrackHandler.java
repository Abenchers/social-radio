package com.abenchers.socialradio.contentinformation.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abenchers.socialradio.common.DeleteTrackRequest;
import com.abenchers.socialradio.common.DeleteTrackResponse;
import com.abenchers.socialradio.common.Track;
import com.abenchers.socialradio.contentinformation.DeleteContentInformation;
import com.abenchers.socialradio.contentinformation.exception.ContentInformationException;

@Component("DeleteTrackHandler")
public class DeleteTrackHandler implements
		ContentInformationHandler<DeleteTrackRequest, DeleteTrackResponse> {

	@Autowired
	private DeleteContentInformation deleteContentInformation;

	@Override
	public DeleteTrackResponse process(DeleteTrackRequest request)
			throws ContentInformationException {
		final DeleteTrackResponse response = new DeleteTrackResponse();
		final Track track = new Track();
		track.setTrackId(request.getTrackId());
		response.setStatus(deleteContentInformation.delete(track));
		return response;
	}

	public void setDeleteContentInformation(
			DeleteContentInformation deleteContentInformation) {
		this.deleteContentInformation = deleteContentInformation;
	}

}
