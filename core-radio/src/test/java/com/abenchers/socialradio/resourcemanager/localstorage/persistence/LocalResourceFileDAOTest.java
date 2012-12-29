package com.abenchers.socialradio.resourcemanager.localstorage.persistence;

import java.io.File;
import java.io.IOException;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class LocalResourceFileDAOTest {

	private static final String NEW_RESOURCE = "new resource";

	private static final String TESTING_FILE = "testingFile.txt";

	private static final String NEW_SECOND_RESOURCE = "second new resource";

	private LocalResourceFileDAO localResourceFileDAO;

	@Rule
	public TemporaryFolder temporalyFolder = new TemporaryFolder();

	@Before
	public void setUp() {
		localResourceFileDAO = new LocalResourceFileDAO();
	}

	@Test
	public void shouldAddResource() throws IOException {

		File file = temporalyFolder.newFile(TESTING_FILE);

		localResourceFileDAO.setResourcesListFileName(file.getAbsolutePath());

		localResourceFileDAO.addResource(NEW_RESOURCE);

		List<String> resources = localResourceFileDAO.listResources();

		Assert.assertTrue(resources.contains(NEW_RESOURCE));
	}

	@Test
	public void shouldRemoveResource() throws IOException {

		File file = temporalyFolder.newFile(TESTING_FILE);

		localResourceFileDAO.setResourcesListFileName(file.getAbsolutePath());

		localResourceFileDAO.addResource(NEW_RESOURCE);

		List<String> resources = localResourceFileDAO.listResources();

		Assert.assertTrue(resources.contains(NEW_RESOURCE));

		localResourceFileDAO.removeResource(NEW_RESOURCE);

		resources = localResourceFileDAO.listResources();

		Assert.assertTrue(resources.isEmpty());

	}

	@Test
	public void shouldListResources() throws IOException {

		File file = temporalyFolder.newFile(TESTING_FILE);

		localResourceFileDAO.setResourcesListFileName(file.getAbsolutePath());

		localResourceFileDAO.addResource(NEW_RESOURCE);

		localResourceFileDAO.addResource(NEW_SECOND_RESOURCE);

		List<String> resources = localResourceFileDAO.listResources();

		Assert.assertTrue(resources.size() == 2);

	}

}
