package com.abenchers.socialradio.contentinformation.metadataextract;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.Closeables;

public class TagExtractorMediaInfo implements TagExtractor {

	private static final Charset CHARSET = Charsets.UTF_8;
	private String mediaInfoDirectory;
	private String mediaInfoApplication;

	@Override
	public String process(final String audioFileLocation) {

		String calculateMediaTechInfo = null;
		InputStreamReader inputStreamReader = null;
		final String applicationMediaTechInfoPath = mediaInfoDirectory
				+ mediaInfoApplication;
		try {
			String[] commands = { applicationMediaTechInfoPath, "-f",
					"--output=XML", audioFileLocation };
			final Process process = Runtime.getRuntime().exec(commands);
			inputStreamReader = new InputStreamReader(process.getInputStream(),
					CHARSET);
			calculateMediaTechInfo = CharStreams.toString(inputStreamReader);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			Closeables.closeQuietly(inputStreamReader);
		}
		return calculateMediaTechInfo;

	}

	public String getMediaInfoDirectory() {
		return mediaInfoDirectory;
	}

	public void setMediaInfoDirectory(String mediaInfoDirectory) {
		this.mediaInfoDirectory = mediaInfoDirectory;
	}

	public String getMediaInfoApplication() {
		return mediaInfoApplication;
	}

	public void setMediaInfoApplication(String mediaInfoApplication) {
		this.mediaInfoApplication = mediaInfoApplication;
	}

}
