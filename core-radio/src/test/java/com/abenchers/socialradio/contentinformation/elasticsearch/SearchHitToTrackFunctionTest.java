package com.abenchers.socialradio.contentinformation.elasticsearch;

import java.util.Map;

import junit.framework.Assert;

import org.elasticsearch.common.collect.Maps;
import org.elasticsearch.search.SearchHit;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.abenchers.socialradio.common.Track;

public class SearchHitToTrackFunctionTest {

	private static final String ALBUM_VALUE = "Album Value";
	private static final String TRACK_ID = "Track Id";
	private SearchHitToTrackFunction function;

	@Before
	public void setUp() {
		function = new SearchHitToTrackFunction();
	}

	@Test
	public void shouldTransform() {
		Map<String, Object> map = Maps.newHashMap();
		map.put("trackId", new String(TRACK_ID));
		map.put("album", new String(ALBUM_VALUE));
		SearchHit searchHit = Mockito.mock(SearchHit.class);
		Mockito.when(searchHit.getSource()).thenReturn(map);

		Track track = function.apply(searchHit);

		Assert.assertNotNull(track);
		Assert.assertEquals(TRACK_ID, track.getTrackId());
		Assert.assertEquals(ALBUM_VALUE, track.getAlbum());
	}
}
