package com.abenchers.socialradio.resourcemanager.localstorage;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.abenchers.socialradio.resourcemanager.common.LocalResourceAction;
import com.abenchers.socialradio.resourcemanager.common.LocalResourceModificationRequest;
import com.abenchers.socialradio.resourcemanager.common.LocalResourceModificationResponse;
import com.abenchers.socialradio.resourcemanager.common.LocalResourceType;
import com.google.common.collect.Iterables;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testContext.xml")
public class LocalStorageIntegrationTest {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Autowired
	private ResourceAdministrationService service;

	@Test
	@Ignore
	public void indexFileThenListedAndDeleted() throws IOException {
		final File file = folder.newFile("someSong.mp3");
		LocalResourceModificationRequest request = new LocalResourceModificationRequest();

		// Indexing file
		final String filePath = file.getAbsolutePath();
		request.setAction(LocalResourceAction.ADD);
		request.setLocalResourceType(LocalResourceType.FILE);
		request.setResource(filePath);

		LocalResourceModificationResponse response = service.process(request);

		// Test that exist
		request.setAction(LocalResourceAction.LIST);
		response = service.process(request);

		Assert.assertEquals(filePath,
				Iterables.getOnlyElement(response.getResourceList()));

		// Deleting
		request.setAction(LocalResourceAction.REMOVE);
		request.setLocalResourceType(LocalResourceType.FILE);
		request.setResource(filePath);

		response = service.process(request);

		// Checking that is removed
		request.setAction(LocalResourceAction.LIST);
		response = service.process(request);

		Assert.assertTrue(response.getResourceList().isEmpty());

	}

}
