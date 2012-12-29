package com.abenchers.socialradio.contentinformation.metadataextract;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.abenchers.socialradio.common.SourceType;

public class TrackInfoExtractorFactoryImplTest {

	private TrackInfoExtractorFactoryImpl factory;

	@Before
	public void setUp() {
		factory = new TrackInfoExtractorFactoryImpl();
	}

	@Test
	public void shouldReturnAExtractorInstance() {

		MediaInfoExtractor extractor = new MediaInfoExtractor();
		factory.setMediaInfoExtractor(extractor);
		TrackInfoExtractor extractorRecieved = factory
				.getTrackInfoExtractor(SourceType.FILE);

		Assert.assertNotNull(extractorRecieved);
		Assert.assertTrue(extractorRecieved instanceof MediaInfoExtractor);

	}

}
