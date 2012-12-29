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
import com.abenchers.socialradio.streaming.common.StreamingRemainingTimeResponse;
import com.abenchers.socialradio.streaming.common.StreamingStartChannelResponse;
import com.abenchers.socialradio.streaming.common.StreamingStatusResponse;

@Component("channelManagerService")
public class ChannelManagerService {
	@Autowired
	private PlaybackService service;

	@Path("/start")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public StreamingStartChannelResponse addChannel(
			StreamingActionRequest streamingActionRequest) {
		return service.start(streamingActionRequest);

	}

	@Path("/isPlaying")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public StreamingStatusResponse isPlaying(
			StreamingActionRequest streamingActionRequest) {
		return service.isPlaying(streamingActionRequest);

	}

	@Path("/{channel}/getRemainingTime")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public StreamingRemainingTimeResponse getRemainingTime(
			@PathParam(value = "channel") String channel) {
		final StreamingActionRequest streamingActionRequest = new StreamingActionRequest();
		streamingActionRequest.setChannel(channel);
		return service.getRemainingTime(streamingActionRequest);

	}

}
