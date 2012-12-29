package com.abenchers.socialradio.streaming.service;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.abenchers.socialradio.streaming.common.StreamingActionRequest;
import com.abenchers.socialradio.streaming.common.StreamingAddRequest;
import com.abenchers.socialradio.streaming.common.StreamingResponse;
import com.abenchers.socialradio.streaming.common.StreamingStatus;

public class StreamingServiceImplTest {

	private static final String FILE_URL = "fileUrl";

	private static final String CHANNEL = "channel";

	private StreamingServiceImpl service;

	private CommandInterface commandInterfaceMock;

	@Before
	public void setUp() {
		service = new StreamingServiceImpl();
		commandInterfaceMock = Mockito.mock(CommandInterface.class);
	}

	@Test
	public void shouldAddChannel() throws Exception {
		StreamingAddRequest streamingAddRequest = new StreamingAddRequest();
		streamingAddRequest.setChannel(CHANNEL);
		streamingAddRequest.setFileUrl(FILE_URL);

		service.setCommandInterface(commandInterfaceMock);

		StreamingResponse response = service.add(streamingAddRequest);

		Assert.assertEquals(StreamingStatus.OK, response.getStreamingStatus());

		Mockito.verify(commandInterfaceMock).add(CHANNEL, FILE_URL);
	}
	
	@Test
	public void shouldFailAddingChannel() throws Exception {
		StreamingAddRequest streamingAddRequest = new StreamingAddRequest();
		streamingAddRequest.setChannel(CHANNEL);
		streamingAddRequest.setFileUrl(FILE_URL);

		service.setCommandInterface(commandInterfaceMock);
		Mockito.doThrow(new Exception("Fail adding channel")).when(commandInterfaceMock).add(CHANNEL, FILE_URL);
		
		StreamingResponse response = service.add(streamingAddRequest);

		Assert.assertEquals(StreamingStatus.FAIL, response.getStreamingStatus());

		Mockito.verify(commandInterfaceMock).add(CHANNEL, FILE_URL);
	}

	@Test
	public void shouldClearChannel() throws Exception {
		StreamingActionRequest streamingActionRequest = new StreamingActionRequest();
		streamingActionRequest.setChannel(CHANNEL);

		service.setCommandInterface(commandInterfaceMock);

		StreamingResponse response = service.clear(streamingActionRequest);

		Assert.assertEquals(StreamingStatus.OK, response.getStreamingStatus());

		Mockito.verify(commandInterfaceMock).clear(CHANNEL);
	}
	
	@Test
	public void shouldFailCleaningChannel() throws Exception {
		StreamingActionRequest streamingActionRequest = new StreamingActionRequest();
		streamingActionRequest.setChannel(CHANNEL);

		service.setCommandInterface(commandInterfaceMock);
		Mockito.doThrow(new Exception("Fail cleanin channel")).when(commandInterfaceMock).clear(CHANNEL);
		
		StreamingResponse response = service.clear(streamingActionRequest);

		Assert.assertEquals(StreamingStatus.FAIL, response.getStreamingStatus());

		Mockito.verify(commandInterfaceMock).clear(CHANNEL);
	}

	@Test
	public void shouldPlayChannel() throws Exception {
		StreamingActionRequest streamingActionRequest = new StreamingActionRequest();
		streamingActionRequest.setChannel(CHANNEL);

		service.setCommandInterface(commandInterfaceMock);

		StreamingResponse response = service.play(streamingActionRequest);

		Assert.assertEquals(StreamingStatus.OK, response.getStreamingStatus());

		Mockito.verify(commandInterfaceMock).play(CHANNEL);
	}
	
	@Test
	public void shouldFailPlayingChannel() throws Exception {
		StreamingActionRequest streamingActionRequest = new StreamingActionRequest();
		streamingActionRequest.setChannel(CHANNEL);

		service.setCommandInterface(commandInterfaceMock);
		Mockito.doThrow(new Exception("Fail playing channel")).when(commandInterfaceMock).play(CHANNEL);
		
		StreamingResponse response = service.play(streamingActionRequest);

		Assert.assertEquals(StreamingStatus.FAIL, response.getStreamingStatus());

		Mockito.verify(commandInterfaceMock).play(CHANNEL);
	}

	@Test
	public void shouldStopChannel() throws Exception {
		StreamingActionRequest streamingActionRequest = new StreamingActionRequest();
		streamingActionRequest.setChannel(CHANNEL);

		service.setCommandInterface(commandInterfaceMock);

		StreamingResponse response = service.stop(streamingActionRequest);

		Assert.assertEquals(StreamingStatus.OK, response.getStreamingStatus());

		Mockito.verify(commandInterfaceMock).stop(CHANNEL);
	}
	
	@Test
	public void shouldFailStopingChannel() throws Exception {
		StreamingActionRequest streamingActionRequest = new StreamingActionRequest();
		streamingActionRequest.setChannel(CHANNEL);

		service.setCommandInterface(commandInterfaceMock);
		Mockito.doThrow(new Exception("Fail playing channel")).when(commandInterfaceMock).stop(CHANNEL);
		
		StreamingResponse response = service.stop(streamingActionRequest);

		Assert.assertEquals(StreamingStatus.FAIL, response.getStreamingStatus());

		Mockito.verify(commandInterfaceMock).stop(CHANNEL);
	}

	@Test
	public void shouldGetListChannel() throws Exception {
		StreamingActionRequest streamingActionRequest = new StreamingActionRequest();
		streamingActionRequest.setChannel(CHANNEL);

		service.setCommandInterface(commandInterfaceMock);

		StreamingResponse response = service
				.getPlaylist(streamingActionRequest);

		Assert.assertEquals(StreamingStatus.OK, response.getStreamingStatus());

		Mockito.verify(commandInterfaceMock).getPlaylist(CHANNEL);
	}
	
	
	@Test
	public void shouldFailGettingListChannel() throws Exception {
		StreamingActionRequest streamingActionRequest = new StreamingActionRequest();
		streamingActionRequest.setChannel(CHANNEL);

		service.setCommandInterface(commandInterfaceMock);

		Mockito.doThrow(new Exception("Fail getting list channel")).when(commandInterfaceMock).getPlaylist(CHANNEL);
		StreamingResponse response = service
				.getPlaylist(streamingActionRequest);

		Assert.assertEquals(StreamingStatus.FAIL, response.getStreamingStatus());

		Mockito.verify(commandInterfaceMock).getPlaylist(CHANNEL);
	}

	@Test
	public void shouldStartChannel() throws Exception {
		StreamingActionRequest streamingActionRequest = new StreamingActionRequest();
		streamingActionRequest.setChannel(CHANNEL);

		service.setCommandInterface(commandInterfaceMock);

		StreamingResponse response = service.start(streamingActionRequest);

		Assert.assertEquals(StreamingStatus.OK, response.getStreamingStatus());

		Mockito.verify(commandInterfaceMock).start(CHANNEL);
	}
	
	@Test
	public void shouldFailStartingChannel() throws Exception {
		StreamingActionRequest streamingActionRequest = new StreamingActionRequest();
		streamingActionRequest.setChannel(CHANNEL);

		service.setCommandInterface(commandInterfaceMock);
		Mockito.doThrow(new Exception("Fail starting channel")).when(commandInterfaceMock).start(CHANNEL);
		
		StreamingResponse response = service.start(streamingActionRequest);

		Assert.assertEquals(StreamingStatus.FAIL, response.getStreamingStatus());

		Mockito.verify(commandInterfaceMock).start(CHANNEL);
	}
	
	@Test
	public void shouldReturnIfChannelIsPlaying() throws Exception {
		StreamingActionRequest streamingActionRequest = new StreamingActionRequest();
		streamingActionRequest.setChannel(CHANNEL);

		service.setCommandInterface(commandInterfaceMock);

		StreamingResponse response = service.isPlaying(streamingActionRequest);

		Assert.assertEquals(StreamingStatus.OK, response.getStreamingStatus());

		Mockito.verify(commandInterfaceMock).isPlaying(CHANNEL);
	}
	
	@Test
	public void shouldFailReturningIfChannelIsPlaying() throws Exception {
		StreamingActionRequest streamingActionRequest = new StreamingActionRequest();
		streamingActionRequest.setChannel(CHANNEL);

		service.setCommandInterface(commandInterfaceMock);

		Mockito.doThrow(new Exception("Fail starting channel")).when(commandInterfaceMock).isPlaying(CHANNEL);
		
		StreamingResponse response = service.isPlaying(streamingActionRequest);

		Assert.assertEquals(StreamingStatus.FAIL, response.getStreamingStatus());

		Mockito.verify(commandInterfaceMock).isPlaying(CHANNEL);
	}
}
