package com.abenchers.socialradio.playlistmanager.job;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.scheduling.TaskScheduler;

public class PlaylistUpdaterJobServiceTest {
	
	private static final String SOME_USER = "someUser";
	private static final String SOME_CHANEL = "someChanel";
	private static final int MAX_MILISECONDS_FOR_NEXT_SONG = 5;
	private PlaylistUpdaterJobService service;
	private TaskScheduler taskSchedulerMock;
	private PlaylistUpdaterJob playlistUpdaterJobMock;
	
	
	@Before
	public void setUp(){
		service = new PlaylistUpdaterJobService();
		playlistUpdaterJobMock = new PlaylistUpdaterJob();
		taskSchedulerMock = Mockito.mock(TaskScheduler.class);
	}
	
	@Test
	public void shouldCreateJob()
	{
		service.setMaxMiliSecondsForNextSong(MAX_MILISECONDS_FOR_NEXT_SONG);
		service.setPlaylistUpdaterJob(playlistUpdaterJobMock);
		service.setTaskScheduler(taskSchedulerMock);
		
		System.out.println(new Date());
		service.generateJob(60000l, SOME_CHANEL, SOME_USER);
	}

}
