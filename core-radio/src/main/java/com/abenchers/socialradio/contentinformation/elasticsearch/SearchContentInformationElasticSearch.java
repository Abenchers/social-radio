package com.abenchers.socialradio.contentinformation.elasticsearch;

import java.util.Arrays;
import java.util.List;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.collect.Lists;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;

import com.abenchers.socialradio.common.Track;
import com.abenchers.socialradio.common.TrackSearch;
import com.abenchers.socialradio.contentinformation.SearchContentInformation;
import com.google.common.collect.Iterables;

public class SearchContentInformationElasticSearch implements
		SearchContentInformation {

	@Autowired
	private ElasticSearchClientFactory builder;

	private SearchHitToTrackFunction searchHitToTrackFunction;

	private static final Integer maxPageSize = 500;
	private static final String INDEX = "tracks";
	private static final SearchHitToTrackFunction SEARCH_HIT_TO_TRACK_FUNCTION = new SearchHitToTrackFunction();

	public List<Track> search(final TrackSearch trackSearch) {
		final List<Track> tracks;
		final Client client = builder.getElasticSearchClient();
		SearchResponse response;
		SearchRequestBuilder searchRequest;

		searchRequest = client.prepareSearch(INDEX);
		searchRequest.setSize(maxPageSize);
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		for (String textToSearch : trackSearch.getFieldToSearch()) {
			textToSearch = textToSearch.trim();
			if (!textToSearch.isEmpty()) {
				boolQueryBuilder.must(QueryBuilders.queryString(textToSearch));
			}
		}
		searchRequest.setQuery(boolQueryBuilder);
		response = searchRequest.execute().actionGet();
		final List<SearchHit> tracksRecieved = Arrays.asList(response.getHits()
				.getHits());

		searchHitToTrackFunction = (searchHitToTrackFunction == null) ? SEARCH_HIT_TO_TRACK_FUNCTION
				: searchHitToTrackFunction;
		tracks = Lists.newArrayList(Iterables.transform(tracksRecieved,
				searchHitToTrackFunction));

		return tracks;
	}

	public void setBuilder(final ElasticSearchClientFactory builder) {
		this.builder = builder;
	}

	public void setSearchHitToTrackFunction(
			final SearchHitToTrackFunction searchHitToTrackFunction) {
		this.searchHitToTrackFunction = searchHitToTrackFunction;
	}

}
