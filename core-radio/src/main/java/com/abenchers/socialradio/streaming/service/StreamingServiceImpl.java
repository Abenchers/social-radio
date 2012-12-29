package com.abenchers.socialradio.streaming.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abenchers.socialradio.streaming.common.StreamingActionRequest;
import com.abenchers.socialradio.streaming.common.StreamingAddRequest;
import com.abenchers.socialradio.streaming.common.StreamingPlaylistResponse;
import com.abenchers.socialradio.streaming.common.StreamingRemainingTimeResponse;
import com.abenchers.socialradio.streaming.common.StreamingResponse;
import com.abenchers.socialradio.streaming.common.StreamingStartChannelResponse;
import com.abenchers.socialradio.streaming.common.StreamingStatus;
import com.abenchers.socialradio.streaming.common.StreamingStatusResponse;

@Component("streamingService")
public class StreamingServiceImpl implements StreamingService {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(StreamingServiceImpl.class);

	@Autowired
	private CommandInterface commandInterface;

	@Override
	public StreamingResponse add(final StreamingAddRequest streamingAddRequest) {
		final StreamingResponse streamingResponse = new StreamingResponse();
		try {
			commandInterface.add(streamingAddRequest.getChannel(),
					streamingAddRequest.getFileUrl());
			streamingResponse.setStreamingStatus(StreamingStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			setStatusError(e, streamingResponse);

		}
		return streamingResponse;
	}

	@Override
	public StreamingStartChannelResponse start(
			final StreamingActionRequest streamingActionRequest) {
		final StreamingStartChannelResponse streamingStartChannelResponse = new StreamingStartChannelResponse();
		try {
			streamingStartChannelResponse.setStreamURL(commandInterface
					.start(streamingActionRequest.getChannel()));
			streamingStartChannelResponse
					.setStreamingStatus(StreamingStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			setStatusError(e, streamingStartChannelResponse);
		}
		return streamingStartChannelResponse;
	}

	@Override
	public StreamingResponse clear(
			final StreamingActionRequest streamingActionRequest) {
		final StreamingResponse streamingResponse = new StreamingResponse();
		try {
			commandInterface.clear(streamingActionRequest.getChannel());
			streamingResponse.setStreamingStatus(StreamingStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			setStatusError(e, streamingResponse);
		}
		return streamingResponse;
	}

	@Override
	public StreamingResponse play(
			final StreamingActionRequest streamingActionRequest) {
		final StreamingResponse streamingResponse = new StreamingResponse();
		try {
			commandInterface.play(streamingActionRequest.getChannel());
			streamingResponse.setStreamingStatus(StreamingStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			setStatusError(e, streamingResponse);
		}

		return streamingResponse;
	}

	@Override
	public StreamingResponse stop(
			final StreamingActionRequest streamingActionRequest) {
		final StreamingResponse streamingResponse = new StreamingResponse();
		try {
			commandInterface.stop(streamingActionRequest.getChannel());
			streamingResponse.setStreamingStatus(StreamingStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			setStatusError(e, streamingResponse);
		}

		return streamingResponse;
	}

	@Override
	public StreamingPlaylistResponse getPlaylist(
			final StreamingActionRequest streamingActionRequest) {
		StreamingPlaylistResponse playlistResponse = new StreamingPlaylistResponse();
		try {
			playlistResponse.setPlaylist(commandInterface
					.getPlaylist(streamingActionRequest.getChannel()));
			playlistResponse.setStreamingStatus(StreamingStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			setStatusError(e, playlistResponse);
		}

		return playlistResponse;
	}

	@Override
	public StreamingRemainingTimeResponse getRemainingTime(
			final StreamingActionRequest streamingActionRequest) {
		final StreamingRemainingTimeResponse response = new StreamingRemainingTimeResponse();
		try {
			response.setRemainingTime(commandInterface
					.getRemainingTime(streamingActionRequest.getChannel()));
			response.setStreamingStatus(StreamingStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			setStatusError(e, response);
		}

		return response;
	}

	@Override
	public StreamingStatusResponse isPlaying(
			final StreamingActionRequest streamingActionRequest) {
		final StreamingStatusResponse response = new StreamingStatusResponse();
		try {
			response.setIsPlaying(commandInterface
					.isPlaying(streamingActionRequest.getChannel()));
			response.setStreamingStatus(StreamingStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			setStatusError(e, response);
		}

		return response;
	}

	private void setStatusError(final Exception e,
			final StreamingResponse streamingResponse) {
		streamingResponse.setStreamingStatus(StreamingStatus.FAIL);
		streamingResponse.setDescription(e.getMessage());
	}

	public CommandInterface getCommandInterface() {
		return commandInterface;
	}

	public void setCommandInterface(final CommandInterface commandInterface) {
		this.commandInterface = commandInterface;
	}

}
