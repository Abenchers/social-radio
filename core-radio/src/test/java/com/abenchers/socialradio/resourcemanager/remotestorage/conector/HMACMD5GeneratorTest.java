package com.abenchers.socialradio.resourcemanager.remotestorage.conector;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class HMACMD5GeneratorTest {

	private HMACMD5Generator hmacmd5Generator;

	private static final String KEY_EXPECTED = "8d107e6b9de6c05c039faf3be230ca5e";
	private static final String KEY_SECRET = "63a649ff4f047f9614932c0b8552539d";

	@Before
	public void setUp() {
		hmacmd5Generator = new HMACMD5Generator();
	}

	@Test
	public void shouldGenerate() throws Exception {

		String algorithm = "HmacMD5";
		String input = "{\"method\": \"getSongSearchResults\", \"parameters\": {\"query\": \"Maddona\", \"country\":\"Argentina\"}, \"header\": {\"wsKey\": \"gatanasios\", \"sessionID\": \"sessionID\"}}";

		String generatedCode = hmacmd5Generator.getHmacMD5(KEY_SECRET, input,
				algorithm);

		Assert.assertEquals(KEY_EXPECTED, generatedCode);
	}

}
