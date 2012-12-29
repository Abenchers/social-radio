package com.abenchers.socialradio.resourcemanager.localstorage;

import java.util.List;

import junit.framework.Assert;

import org.elasticsearch.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

public class AceptedFileExtensionPredicateTest {

	private AceptedFileExtensionPredicate acceptedFileExtensionPredicate;
	private List<String> acceptedExtensions;
	private static final String FILE_TO_FILTER_MP3 = "/opt/somedirectory/somfile.mp3";
	private static final String FILE_TO_FILTER_OGG = "/opt/somedirectory/somfile.ogg";
	private static final String FILE_TO_FILTER_WAV = "/opt/somedirectory/somfile.wav";
	private static final String FILE_TO_FILTER_BAD_EXTENSION = "/opt/somedirectory/somfile.doc";

	@Before
	public void setUp() {
		acceptedFileExtensionPredicate = new AceptedFileExtensionPredicate();
		acceptedExtensions = Lists.newArrayList();
		acceptedExtensions.add("mp3");
		acceptedExtensions.add("ogg");
		acceptedExtensions.add("wav");
		acceptedFileExtensionPredicate.setExtensionsList(acceptedExtensions);
	}

	@Test
	public void shouldMatch() {

		Assert.assertTrue(acceptedFileExtensionPredicate
				.apply(FILE_TO_FILTER_MP3));
		Assert.assertTrue(acceptedFileExtensionPredicate
				.apply(FILE_TO_FILTER_OGG));
		Assert.assertTrue(acceptedFileExtensionPredicate
				.apply(FILE_TO_FILTER_WAV));

	}

	@Test
	public void shouldNotMatch() {

		Assert.assertFalse(acceptedFileExtensionPredicate
				.apply(FILE_TO_FILTER_BAD_EXTENSION));

	}
}
