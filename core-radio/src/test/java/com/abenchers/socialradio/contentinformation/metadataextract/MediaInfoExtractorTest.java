package com.abenchers.socialradio.contentinformation.metadataextract;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.xml.sax.SAXException;

import com.abenchers.socialradio.common.Track;
import com.abenchers.socialradio.contentinformation.exception.ContentInformationException;
import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class MediaInfoExtractorTest {

	private static final String DURATION = "13mn 16s 787ms";
	private static final String DEFULT_KEY = "defultKey";
	private MediaInfoExtractor mediaInfoExtractor;
	private TagExtractor tagExtractorMock;
	private Map<String, Object> trackSchemaMock;
	private DOMMediaInfoParser parserMock;

	@Before
	public void setUp() {
		mediaInfoExtractor = new MediaInfoExtractor();
		tagExtractorMock = Mockito.mock(TagExtractor.class);
		parserMock = Mockito.mock(DOMMediaInfoParser.class);
		trackSchemaMock = (Map<String, Object>) Mockito.mock(Map.class);
	}

	@Test
	public void shouldExtractFromMediaInfo() throws IOException,
			XPathExpressionException, ContentInformationException {
		String source = "someSource";
		String xmlMediaInfo = Files.toString(
				new File(getClass().getClassLoader()
						.getResource("mediaInfoExample.xml").getFile()),
				Charsets.UTF_8);
		Mockito.when(tagExtractorMock.process(source)).thenReturn(xmlMediaInfo);

		
		Mockito.when(parserMock.extractMediaInfo(Mockito.anyString()))
			.thenReturn(DURATION);

		//Mockito.when(trackSchemaMock.get(DURATION_KEY)).thenReturn(DURATION);
		Mockito.when(trackSchemaMock.get(Mockito.anyString())).thenReturn(
				DEFULT_KEY);
		mediaInfoExtractor.setTrackSchema(trackSchemaMock);
		mediaInfoExtractor.setTagExtractor(tagExtractorMock);
		mediaInfoExtractor.setParser(parserMock);

		Track track = mediaInfoExtractor.extractTrackInfo(source);
		Assert.assertEquals(extractDurationFromString(DURATION), track.getDuration());
	}

	@Test(expected = ContentInformationException.class)
	public void shouldFailCouldNotParse() throws IOException,
			ContentInformationException, XPathExpressionException {
		String source = "someSource";
		String xmlMediaInfo = Files.toString(
				new File(getClass().getClassLoader()
						.getResource("mediaInfoExample.xml").getFile()),
				Charsets.UTF_8);
		Mockito.when(tagExtractorMock.process(source)).thenReturn(xmlMediaInfo);

		Mockito.when(parserMock.extractMediaInfo(Mockito.anyString()))
				.thenThrow(new XPathExpressionException("Error"));

		Mockito.when(trackSchemaMock.get(Mockito.anyString())).thenReturn(
				DEFULT_KEY);
		mediaInfoExtractor.setTrackSchema(trackSchemaMock);
		mediaInfoExtractor.setTagExtractor(tagExtractorMock);
		mediaInfoExtractor.setParser(parserMock);

		mediaInfoExtractor.extractTrackInfo(source);
	}

	@Test(expected = ContentInformationException.class)
	public void shouldFailCreatingDocument() throws IOException,
			ContentInformationException, XPathExpressionException,
			ParserConfigurationException, SAXException {
		String source = "someSource";
		String mediaInfoXML = "mediaInfoXML";
		Mockito.when(tagExtractorMock.process(source)).thenReturn(mediaInfoXML);

		Mockito.doThrow(new SAXException()).when(parserMock).init(mediaInfoXML);

		mediaInfoExtractor.setTrackSchema(trackSchemaMock);
		mediaInfoExtractor.setTagExtractor(tagExtractorMock);
		mediaInfoExtractor.setParser(parserMock);

		mediaInfoExtractor.extractTrackInfo(source);
	}

	@Test(expected = ContentInformationException.class)
	public void shouldFailConfigurationParser() throws IOException,
			ContentInformationException, XPathExpressionException,
			ParserConfigurationException, SAXException {
		String source = "someSource";
		String mediaInfoXML = "mediaInfoXML";
		Mockito.when(tagExtractorMock.process(source)).thenReturn(mediaInfoXML);

		Mockito.doThrow(new ParserConfigurationException()).when(parserMock)
				.init(mediaInfoXML);

		mediaInfoExtractor.setTrackSchema(trackSchemaMock);
		mediaInfoExtractor.setTagExtractor(tagExtractorMock);
		mediaInfoExtractor.setParser(parserMock);

		mediaInfoExtractor.extractTrackInfo(source);
	}
	
	private String extractDurationFromString(String extractMediaInfo) {
		String myStr = extractMediaInfo.replaceAll("[^\\d]", " ");
		String[] myStrParts = myStr.split("\\s+");
		Long minutes = Long.valueOf(myStrParts[0]) * 60l * 1000l;
		Long seconds = Long.valueOf(myStrParts[1]) * 1000l;
		Long total = minutes + seconds + Long.valueOf(myStrParts[2]);
		return String.valueOf(total);
	}
}
