package com.abenchers.socialradio.playlistmanager.service;

import com.abenchers.socialradio.playlistmanager.common.AlterPlayListRequest;
import com.abenchers.socialradio.playlistmanager.common.AlterPlayListResponse;

public interface PlayListManagerService {

	AlterPlayListResponse modifyPlayList(AlterPlayListRequest request);
}
