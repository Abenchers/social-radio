package com.abenchers.socialradio.playlistmanager.handler;

import com.abenchers.socialradio.playlistmanager.common.AlterPlayListRequest;
import com.abenchers.socialradio.playlistmanager.common.AlterPlayListResponse;

public interface PlayListManagerHandler {

	AlterPlayListResponse process(AlterPlayListRequest request);

}
