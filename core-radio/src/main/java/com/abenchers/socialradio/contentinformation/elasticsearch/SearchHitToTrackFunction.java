package com.abenchers.socialradio.contentinformation.elasticsearch;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.search.SearchHit;

import com.abenchers.socialradio.common.Track;
import com.google.common.base.Function;

public class SearchHitToTrackFunction implements Function<SearchHit, Track> {

	public Track apply(SearchHit searchHit) {
		Track track = null;
		Writer result = new StringWriter();
		final ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(result, searchHit.getSource());
			track = (mapper.readValue(result.toString(), Track.class));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return track;
	}

}
