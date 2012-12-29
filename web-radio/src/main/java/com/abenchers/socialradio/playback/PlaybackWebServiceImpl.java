package com.abenchers.socialradio.playback;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abenchers.socialradio.streaming.common.StreamingActionRequest;
import com.abenchers.socialradio.streaming.common.StreamingAddRequest;
import com.abenchers.socialradio.streaming.common.StreamingResponse;
import com.abenchers.socialradio.streaming.common.StreamingStatus;

@Component("playbackWebService")
public class PlaybackWebServiceImpl {

	private static final String CLEAR = "clear";
	private static final String STOP_ACTION = "stop";
	private static final String PLAY_ACTION = "play";
	@Autowired
	private PlaybackService service;

	@Path("/{channel}/{action}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public StreamingResponse playbackModification(
			@PathParam(value = "channel") String channel,
			@PathParam(value = "action") String acction) {
		final StreamingActionRequest streamingActionRequest = new StreamingActionRequest();
		StreamingResponse streamingResponse = new StreamingResponse();
		streamingActionRequest.setChannel(channel);

		streamingResponse.setStreamingStatus(StreamingStatus.FAIL);

		if (acction.equalsIgnoreCase(PLAY_ACTION))
			streamingResponse = service.play(streamingActionRequest);
		if (acction.equalsIgnoreCase(STOP_ACTION))
			streamingResponse = service.stop(streamingActionRequest);
		if (acction.equalsIgnoreCase(CLEAR))
			streamingResponse = service.clear(streamingActionRequest);

		return streamingResponse;

	}

	@Path("/add")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public StreamingResponse addChannel(StreamingAddRequest streamingAddRequest) {
		return service.add(streamingAddRequest);

	}

	@Path("/{channel}/getPlayingList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public StreamingResponse getStreamingList(
			@PathParam(value = "channel") String channel) {
		final StreamingActionRequest streamingActionRequest = new StreamingActionRequest();
		streamingActionRequest.setChannel(channel);
		return service.getPlaylist(streamingActionRequest);

	}
	
	
}
