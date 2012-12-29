package com.abenchers.socialradio.contentinformation.metadataextract;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class DOMMediaInfoParserTest {

	private DOMMediaInfoParser domMediaInfoParser;

	private static final String KEY_TO_FIND = "//Performer/text()";

	private static final String VALUE_FOUND = "Los Redondos";

	@Before
	public void setUp() {
		domMediaInfoParser = new DOMMediaInfoParser();
	}

	@Test
	public void shouldParse() throws IOException, ParserConfigurationException,
			SAXException, XPathExpressionException {
		String value = Files
				.toString(
						new File(getClass().getClassLoader()
								.getResource("mediaInfoExample.xml").getFile()),
						Charsets.UTF_8);

		domMediaInfoParser.init(value);

		String returnedValue = domMediaInfoParser.extractMediaInfo(KEY_TO_FIND);

		Assert.assertEquals(VALUE_FOUND, returnedValue);

	}

	@Test(expected = SAXException.class)
	public void shouldFailGeneratingDom() throws IOException,
			ParserConfigurationException, SAXException,
			XPathExpressionException {
		String value = "";

		domMediaInfoParser.init(value);

		String returnedValue = domMediaInfoParser.extractMediaInfo(KEY_TO_FIND);

		Assert.assertEquals(VALUE_FOUND, returnedValue);

	}

}
