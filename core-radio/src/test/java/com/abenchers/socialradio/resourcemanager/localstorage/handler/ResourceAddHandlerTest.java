package com.abenchers.socialradio.resourcemanager.localstorage.handler;

import java.io.File;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mockito;

import com.abenchers.socialradio.common.IndexResponse;
import com.abenchers.socialradio.common.IndexTrack;
import com.abenchers.socialradio.common.ResponseStatus;
import com.abenchers.socialradio.contentinformation.service.ContentInformationService;
import com.abenchers.socialradio.resourcemanager.common.LocalResourceAction;
import com.abenchers.socialradio.resourcemanager.common.LocalResourceModificationRequest;
import com.abenchers.socialradio.resourcemanager.common.LocalResourceModificationResponse;
import com.abenchers.socialradio.resourcemanager.common.LocalResourceType;
import com.abenchers.socialradio.resourcemanager.localstorage.AceptedFileExtensionPredicate;
import com.abenchers.socialradio.resourcemanager.localstorage.persistence.LocalResourceDAO;

public class ResourceAddHandlerTest {

	private static final String RESOURCE = "resource";

	private static final String BAD_RESOURCE = "NonExistingResource";

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	private ResourceAddHandler handler;

	private ContentInformationService contentInformationServiceMock;
	private LocalResourceDAO localResourceDAOMock;
	private AceptedFileExtensionPredicate aceptedFileExtensionPredicateMock;

	@Before
	public void setUp() {
		handler = new ResourceAddHandler();

		contentInformationServiceMock = Mockito
				.mock(ContentInformationService.class);
		localResourceDAOMock = Mockito.mock(LocalResourceDAO.class);
		aceptedFileExtensionPredicateMock = Mockito
				.mock(AceptedFileExtensionPredicate.class);
	}

	@After
	public void cleanUp() {
		temporaryFolder.delete();
	}

	@Test
	public void shouldHandler() {

		File file = temporaryFolder.newFolder(RESOURCE);
		LocalResourceModificationRequest request = new LocalResourceModificationRequest();
		request.setAction(LocalResourceAction.ADD);
		request.setResource(file.getAbsolutePath());
		request.setLocalResourceType(LocalResourceType.DIRECTORY);

		Mockito.when(localResourceDAOMock.addResource(file.getAbsolutePath()))
				.thenReturn(true);
		Mockito.when(
				contentInformationServiceMock.index(Mockito
						.any(IndexTrack.class)))
				.thenReturn(new IndexResponse());

		Mockito.when(
				aceptedFileExtensionPredicateMock.apply(Mockito
						.any(String.class))).thenReturn(true);

		handler.setContentInformationService(contentInformationServiceMock);
		handler.setLocalResourceDAO(localResourceDAOMock);
		handler.setAceptedFileExtensionPredicate(aceptedFileExtensionPredicateMock);

		LocalResourceModificationResponse response = handler.process(request);

		Assert.assertEquals(ResponseStatus.SUCCESS, response.getStatus());

	}

	@Test
	public void shouldFailHandlingResourceDontExist() {

		LocalResourceModificationRequest request = new LocalResourceModificationRequest();
		request.setAction(LocalResourceAction.ADD);
		request.setResource(BAD_RESOURCE);
		request.setLocalResourceType(LocalResourceType.DIRECTORY);

		LocalResourceModificationResponse response = handler.process(request);

		Assert.assertSame(ResponseStatus.FAIL, response.getStatus());
	}

}
