package org.playback;

public interface playbackServiceInterface {

public long nextTrackToBeReproduce(long userId, long listId);

public boolean changeToNextSong();

public String nextTracktoBeStreaming (long channelId, long path);

public Track getTrackInformation (long trackId); 

public Track getUserInformation (long userId); 

public boolean stopStreaming(long channelId);

public String streamNextTrack(long oldChannelId, long newChannelId, long path);
	
}


