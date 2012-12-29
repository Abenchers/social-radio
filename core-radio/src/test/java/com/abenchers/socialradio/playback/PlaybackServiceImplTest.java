package com.abenchers.socialradio.playback;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.abenchers.socialradio.streaming.common.StreamingActionRequest;
import com.abenchers.socialradio.streaming.common.StreamingAddRequest;
import com.abenchers.socialradio.streaming.common.StreamingPlaylistResponse;
import com.abenchers.socialradio.streaming.common.StreamingRemainingTimeResponse;
import com.abenchers.socialradio.streaming.common.StreamingResponse;
import com.abenchers.socialradio.streaming.common.StreamingStartChannelResponse;
import com.abenchers.socialradio.streaming.common.StreamingStatus;
import com.abenchers.socialradio.streaming.common.StreamingStatusResponse;
import com.abenchers.socialradio.streaming.service.StreamingService;

public class PlaybackServiceImplTest {

	private static final String FILE_URL = "fileUrl";
	private static final String STREAMING_URL = "www.someurl.com";
	private PlaybackServiceImpl playbackService;
	private StreamingService streamingServiceMock;

	@Before
	public void setUp() {
		playbackService = new PlaybackServiceImpl();
		streamingServiceMock = Mockito.mock(StreamingService.class);
	}

	@Test
	public void shouldStartAChannel() {

		StreamingActionRequest streamingActionRequest = new StreamingActionRequest();
		streamingActionRequest.setChannel("someChannel");

		StreamingStartChannelResponse response = new StreamingStartChannelResponse();
		response.setStreamURL(STREAMING_URL);
		response.setStreamingStatus(StreamingStatus.OK);

		Mockito.when(streamingServiceMock.start(streamingActionRequest))
				.thenReturn(response);

		playbackService.setStreamingService(streamingServiceMock);

		StreamingResponse streamingResponse = playbackService
				.start(streamingActionRequest);

		Assert.assertTrue(streamingResponse.getStreamingStatus().equals(
				StreamingStatus.OK));

		Mockito.verify(streamingServiceMock).start(streamingActionRequest);

	}

	@Test
	public void shouldNotStartAChannel() {

		StreamingActionRequest streamingActionRequest = new StreamingActionRequest();
		streamingActionRequest.setChannel("someChannel");

		StreamingStartChannelResponse response = new StreamingStartChannelResponse();
		response.setStreamURL(STREAMING_URL);
		response.setStreamingStatus(StreamingStatus.FAIL);

		Mockito.when(streamingServiceMock.start(streamingActionRequest))
				.thenReturn(response);

		playbackService.setStreamingService(streamingServiceMock);

		StreamingResponse streamingResponse = playbackService
				.start(streamingActionRequest);

		Assert.assertTrue(streamingResponse.getStreamingStatus().equals(
				StreamingStatus.FAIL));

		Mockito.verify(streamingServiceMock).start(streamingActionRequest);

	}

	@Test
	public void shouldAddASongtoAChannel() {

		StreamingAddRequest streamingAddRequest = new StreamingAddRequest();
		streamingAddRequest.setChannel("someChannel");
		streamingAddRequest.setFileUrl(FILE_URL);

		StreamingStartChannelResponse response = new StreamingStartChannelResponse();
		response.setStreamURL(STREAMING_URL);
		response.setStreamingStatus(StreamingStatus.OK);

		Mockito.when(streamingServiceMock.add(streamingAddRequest)).thenReturn(
				response);

		playbackService.setStreamingService(streamingServiceMock);

		StreamingResponse streamingResponse = playbackService
				.add(streamingAddRequest);

		Assert.assertTrue(streamingResponse.getStreamingStatus().equals(
				StreamingStatus.OK));

		Mockito.verify(streamingServiceMock).add(streamingAddRequest);

	}

	@Test
	public void shouldNotAddASongtoAChannel() {

		StreamingAddRequest streamingAddRequest = new StreamingAddRequest();
		streamingAddRequest.setChannel("someChannel");
		streamingAddRequest.setFileUrl(FILE_URL);

		StreamingStartChannelResponse response = new StreamingStartChannelResponse();
		response.setStreamURL(STREAMING_URL);
		response.setStreamingStatus(StreamingStatus.FAIL);

		Mockito.when(streamingServiceMock.add(streamingAddRequest)).thenReturn(
				response);

		playbackService.setStreamingService(streamingServiceMock);

		StreamingResponse streamingResponse = playbackService
				.add(streamingAddRequest);

		Assert.assertTrue(streamingResponse.getStreamingStatus().equals(
				StreamingStatus.FAIL));

		Mockito.verify(streamingServiceMock).add(streamingAddRequest);

	}

	@Test
	public void shouldClearAChannel() {

		StreamingActionRequest streamingActionRequest = new StreamingActionRequest();
		streamingActionRequest.setChannel("someChannel");

		StreamingStartChannelResponse response = new StreamingStartChannelResponse();
		response.setStreamURL(STREAMING_URL);
		response.setStreamingStatus(StreamingStatus.OK);

		Mockito.when(streamingServiceMock.clear(streamingActionRequest))
				.thenReturn(response);

		playbackService.setStreamingService(streamingServiceMock);

		StreamingResponse streamingResponse = playbackService
				.clear(streamingActionRequest);

		Assert.assertTrue(streamingResponse.getStreamingStatus().equals(
				StreamingStatus.OK));

		Mockito.verify(streamingServiceMock).clear(streamingActionRequest);

	}

	@Test
	public void shouldNotClearAChannel() {

		StreamingActionRequest streamingActionRequest = new StreamingActionRequest();
		streamingActionRequest.setChannel("someChannel");

		StreamingStartChannelResponse response = new StreamingStartChannelResponse();
		response.setStreamURL(STREAMING_URL);
		response.setStreamingStatus(StreamingStatus.FAIL);

		Mockito.when(streamingServiceMock.clear(streamingActionRequest))
				.thenReturn(response);

		playbackService.setStreamingService(streamingServiceMock);

		StreamingResponse streamingResponse = playbackService
				.clear(streamingActionRequest);

		Assert.assertTrue(streamingResponse.getStreamingStatus().equals(
				StreamingStatus.FAIL));

		Mockito.verify(streamingServiceMock).clear(streamingActionRequest);

	}

	@Test
	public void shouldResponseChannelIsPLaying() {

		StreamingActionRequest streamingActionRequest = new StreamingActionRequest();
		streamingActionRequest.setChannel("someChannel");

		StreamingStatusResponse response = new StreamingStatusResponse();
		response.setStreamingStatus(StreamingStatus.OK);
		response.setIsPlaying(true);

		Mockito.when(streamingServiceMock.isPlaying(streamingActionRequest))
				.thenReturn(response);

		playbackService.setStreamingService(streamingServiceMock);

		StreamingStatusResponse streamingResponse = playbackService
				.isPlaying(streamingActionRequest);

		Assert.assertTrue(streamingResponse.getStreamingStatus().equals(
				StreamingStatus.OK));
		Assert.assertTrue(streamingResponse.isPlaying());

		Mockito.verify(streamingServiceMock).isPlaying(streamingActionRequest);

	}

	@Test
	public void shouldResponseIsNotPLaying() {

		StreamingActionRequest streamingActionRequest = new StreamingActionRequest();
		streamingActionRequest.setChannel("someChannel");

		StreamingStatusResponse response = new StreamingStatusResponse();
		response.setStreamingStatus(StreamingStatus.OK);
		response.setIsPlaying(false);

		Mockito.when(streamingServiceMock.isPlaying(streamingActionRequest))
				.thenReturn(response);

		playbackService.setStreamingService(streamingServiceMock);

		StreamingStatusResponse streamingResponse = playbackService
				.isPlaying(streamingActionRequest);

		Assert.assertTrue(streamingResponse.getStreamingStatus().equals(
				StreamingStatus.OK));

		Assert.assertTrue(!streamingResponse.isPlaying());

		Mockito.verify(streamingServiceMock).isPlaying(streamingActionRequest);

	}

	@Test
	public void shouldFailedChannelIsPLaying() {

		StreamingActionRequest streamingActionRequest = new StreamingActionRequest();
		streamingActionRequest.setChannel("someChannel");

		StreamingStatusResponse response = new StreamingStatusResponse();
		response.setStreamingStatus(StreamingStatus.FAIL);
		response.setIsPlaying(true);

		Mockito.when(streamingServiceMock.isPlaying(streamingActionRequest))
				.thenReturn(response);

		playbackService.setStreamingService(streamingServiceMock);

		StreamingResponse streamingResponse = playbackService
				.isPlaying(streamingActionRequest);

		Assert.assertTrue(streamingResponse.getStreamingStatus().equals(
				StreamingStatus.FAIL));

		Mockito.verify(streamingServiceMock).isPlaying(streamingActionRequest);

	}

	@Test
	public void shouldPlayAChannel() {

		StreamingActionRequest streamingActionRequest = new StreamingActionRequest();
		streamingActionRequest.setChannel("someChannel");

		StreamingStartChannelResponse response = new StreamingStartChannelResponse();
		response.setStreamingStatus(StreamingStatus.OK);

		Mockito.when(streamingServiceMock.play(streamingActionRequest))
				.thenReturn(response);

		playbackService.setStreamingService(streamingServiceMock);

		StreamingResponse streamingResponse = playbackService
				.play(streamingActionRequest);

		Assert.assertTrue(streamingResponse.getStreamingStatus().equals(
				StreamingStatus.OK));

		Mockito.verify(streamingServiceMock).play(streamingActionRequest);

	}

	@Test
	public void shouldFailPlayAChannel() {

		StreamingActionRequest streamingActionRequest = new StreamingActionRequest();
		streamingActionRequest.setChannel("someChannel");

		StreamingStartChannelResponse response = new StreamingStartChannelResponse();
		response.setStreamingStatus(StreamingStatus.FAIL);

		Mockito.when(streamingServiceMock.play(streamingActionRequest))
				.thenReturn(response);

		playbackService.setStreamingService(streamingServiceMock);

		StreamingResponse streamingResponse = playbackService
				.play(streamingActionRequest);

		Assert.assertTrue(streamingResponse.getStreamingStatus().equals(
				StreamingStatus.FAIL));

		Mockito.verify(streamingServiceMock).play(streamingActionRequest);

	}

	@Test
	public void shouldStopAChannel() {

		StreamingActionRequest streamingActionRequest = new StreamingActionRequest();
		streamingActionRequest.setChannel("someChannel");

		StreamingStartChannelResponse response = new StreamingStartChannelResponse();
		response.setStreamingStatus(StreamingStatus.OK);

		Mockito.when(streamingServiceMock.stop(streamingActionRequest))
				.thenReturn(response);

		playbackService.setStreamingService(streamingServiceMock);

		StreamingResponse streamingResponse = playbackService
				.stop(streamingActionRequest);

		Assert.assertTrue(streamingResponse.getStreamingStatus().equals(
				StreamingStatus.OK));

		Mockito.verify(streamingServiceMock).stop(streamingActionRequest);

	}

	@Test
	public void shouldFiledStopingAChannel() {

		StreamingActionRequest streamingActionRequest = new StreamingActionRequest();
		streamingActionRequest.setChannel("someChannel");

		StreamingStartChannelResponse response = new StreamingStartChannelResponse();
		response.setStreamingStatus(StreamingStatus.FAIL);

		Mockito.when(streamingServiceMock.stop(streamingActionRequest))
				.thenReturn(response);

		playbackService.setStreamingService(streamingServiceMock);

		StreamingResponse streamingResponse = playbackService
				.stop(streamingActionRequest);

		Assert.assertTrue(streamingResponse.getStreamingStatus().equals(
				StreamingStatus.FAIL));

		Mockito.verify(streamingServiceMock).stop(streamingActionRequest);

	}

	@Test
	public void shouldGetPlaylistFromAChannel() {

		StreamingActionRequest streamingActionRequest = new StreamingActionRequest();
		streamingActionRequest.setChannel("someChannel");

		StreamingPlaylistResponse response = new StreamingPlaylistResponse();
		response.setStreamingStatus(StreamingStatus.OK);
		response.setPlaylist(new ArrayList<String>());

		Mockito.when(streamingServiceMock.getPlaylist(streamingActionRequest))
				.thenReturn(response);

		playbackService.setStreamingService(streamingServiceMock);

		StreamingPlaylistResponse streamingResponse = playbackService
				.getPlaylist(streamingActionRequest);

		Assert.assertTrue(streamingResponse.getStreamingStatus().equals(
				StreamingStatus.OK));
		Assert.assertNotNull(streamingResponse.getPlaylist());

		Mockito.verify(streamingServiceMock)
				.getPlaylist(streamingActionRequest);

	}

	@Test
	public void shouldFailGetingPlaylistFromAChannel() {

		StreamingActionRequest streamingActionRequest = new StreamingActionRequest();
		streamingActionRequest.setChannel("someChannel");

		StreamingPlaylistResponse response = new StreamingPlaylistResponse();
		response.setStreamingStatus(StreamingStatus.FAIL);
		response.setPlaylist(new ArrayList<String>());

		Mockito.when(streamingServiceMock.getPlaylist(streamingActionRequest))
				.thenReturn(response);

		playbackService.setStreamingService(streamingServiceMock);

		StreamingPlaylistResponse streamingResponse = playbackService
				.getPlaylist(streamingActionRequest);

		Assert.assertTrue(streamingResponse.getStreamingStatus().equals(
				StreamingStatus.FAIL));
		Assert.assertNotNull(streamingResponse.getPlaylist());

		Mockito.verify(streamingServiceMock)
				.getPlaylist(streamingActionRequest);

	}

	@Test
	public void shouldGetRemainingTimeFromAChannel() {

		StreamingActionRequest streamingActionRequest = new StreamingActionRequest();
		streamingActionRequest.setChannel("someChannel");

		StreamingRemainingTimeResponse response = new StreamingRemainingTimeResponse();
		response.setStreamingStatus(StreamingStatus.OK);
		response.setRemainingTime(400l);

		Mockito.when(
				streamingServiceMock.getRemainingTime(streamingActionRequest))
				.thenReturn(response);

		playbackService.setStreamingService(streamingServiceMock);

		StreamingRemainingTimeResponse streamingResponse = playbackService
				.getRemainingTime(streamingActionRequest);

		Assert.assertTrue(streamingResponse.getStreamingStatus().equals(
				StreamingStatus.OK));

		Assert.assertTrue(streamingResponse.getRemainingTime().equals(400l));

		Mockito.verify(streamingServiceMock).getRemainingTime(
				streamingActionRequest);

	}

	@Test
	public void shouldFailGettingRemainingTimeFromAChannel() {

		StreamingActionRequest streamingActionRequest = new StreamingActionRequest();
		streamingActionRequest.setChannel("someChannel");

		StreamingRemainingTimeResponse response = new StreamingRemainingTimeResponse();
		response.setStreamingStatus(StreamingStatus.FAIL);
		response.setRemainingTime(400l);

		Mockito.when(
				streamingServiceMock.getRemainingTime(streamingActionRequest))
				.thenReturn(response);

		playbackService.setStreamingService(streamingServiceMock);

		StreamingRemainingTimeResponse streamingResponse = playbackService
				.getRemainingTime(streamingActionRequest);

		Assert.assertTrue(streamingResponse.getStreamingStatus().equals(
				StreamingStatus.FAIL));

		Assert.assertTrue(streamingResponse.getRemainingTime().equals(400l));

		Mockito.verify(streamingServiceMock).getRemainingTime(
				streamingActionRequest);

	}
}
