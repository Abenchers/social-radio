package com.abenchers.socialradio.resourcemanager.localstorage.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.elasticsearch.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Files;

public class LocalResourceFileDAO implements LocalResourceDAO {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(LocalResourceFileDAO.class);

	private String resourcesListFileName;

	private File resourcesFile;

	@Override
	public Boolean addResource(String resource) {
		initFile();
		Boolean status = null;

		try {
			FileWriter fstream = new FileWriter(resourcesListFileName, true);
			BufferedWriter fbw = new BufferedWriter(fstream);
			fbw.write(resource);
			fbw.newLine();
			fbw.close();
			status = true;
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
			status = false;
		}
		return status;
	}

	@Override
	public Boolean removeResource(String resource) {
		initFile();
		Boolean status = null;
		try {
			final String tempFileName = resourcesFile.getAbsolutePath()
					+ ".tmp";
			final FileWriter temporalFileWriter = new FileWriter(tempFileName,
					true);
			final BufferedWriter tempFileWriter = new BufferedWriter(
					temporalFileWriter);

			final BufferedReader readFile = new BufferedReader(new FileReader(
					resourcesFile));
			String line;
			while ((line = readFile.readLine()) != null) {
				if (!line.trim().equals(resource)) {
					tempFileWriter.write(resource);
					tempFileWriter.newLine();

				}
			}
			tempFileWriter.close();
			readFile.close();

			resourcesFile.delete();
			Files.move(new File(tempFileName),
					new File(resourcesFile.getAbsolutePath()));
			status = true;
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
			status = false;
		}
		return status;
	}

	@Override
	public List<String> listResources() {
		initFile();
		final List<String> resourcesList = Lists.newArrayList();
		String line;
		try {
			BufferedReader br = new BufferedReader(
					new FileReader(resourcesFile));
			while ((line = br.readLine()) != null) {
				resourcesList.add(line);
			}
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		return resourcesList;
	}

	private void initFile() {
		resourcesFile = new File(resourcesListFileName);
	}

	public String getResourcesListFileName() {
		return resourcesListFileName;
	}

	public void setResourcesListFileName(String resourcesListFileName) {
		this.resourcesListFileName = resourcesListFileName;
	}


}
