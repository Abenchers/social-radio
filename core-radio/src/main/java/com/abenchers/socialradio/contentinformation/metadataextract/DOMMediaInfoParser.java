package com.abenchers.socialradio.contentinformation.metadataextract;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DOMMediaInfoParser {

	private Document document;

	public void init(String process) throws ParserConfigurationException,
			SAXException, IOException {
		final DocumentBuilderFactory factory = DocumentBuilderFactory
				.newInstance();
		factory.setNamespaceAware(true);
		final DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(process));

		document = builder.parse(is);
	}

	public String extractMediaInfo(final String key) throws XPathExpressionException {
		String value = null;


			final XPathFactory xPathFactory = XPathFactory.newInstance();
			final XPath xpath = xPathFactory.newXPath();
			final XPathExpression expr = xpath.compile(key);
			value = expr.evaluate(document, XPathConstants.STRING).toString();

	
		return value;
	}

}
