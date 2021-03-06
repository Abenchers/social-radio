package com.abenchers.socialradio.streaming.service;

import org.springframework.stereotype.Component;

import com.abenchers.socialradio.streaming.common.StreamingActionRequest;
import com.abenchers.socialradio.streaming.common.StreamingAddRequest;
import com.abenchers.socialradio.streaming.common.StreamingPlaylistResponse;
import com.abenchers.socialradio.streaming.common.StreamingRemainingTimeResponse;
import com.abenchers.socialradio.streaming.common.StreamingResponse;
import com.abenchers.socialradio.streaming.common.StreamingStartChannelResponse;
import com.abenchers.socialradio.streaming.common.StreamingStatusResponse;

@Component("streamingService")
public interface StreamingService {

	StreamingResponse add(StreamingAddRequest streamingAddRequest);

	StreamingStartChannelResponse start(
			StreamingActionRequest streamingActionRequest);

	StreamingResponse clear(StreamingActionRequest streamingActionRequest);

	StreamingResponse play(StreamingActionRequest streamingActionRequest);

	StreamingResponse stop(StreamingActionRequest streamingActionRequest);

	StreamingPlaylistResponse getPlaylist(
			StreamingActionRequest streamingActionRequest);

	StreamingRemainingTimeResponse getRemainingTime(
			StreamingActionRequest streamingActionRequest);

	StreamingStatusResponse isPlaying(
			StreamingActionRequest streamingActionRequest);

}
