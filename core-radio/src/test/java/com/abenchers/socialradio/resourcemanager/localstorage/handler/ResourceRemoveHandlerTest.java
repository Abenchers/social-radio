package com.abenchers.socialradio.resourcemanager.localstorage.handler;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.abenchers.socialradio.common.ResponseStatus;
import com.abenchers.socialradio.resourcemanager.common.LocalResourceAction;
import com.abenchers.socialradio.resourcemanager.common.LocalResourceModificationRequest;
import com.abenchers.socialradio.resourcemanager.common.LocalResourceModificationResponse;
import com.abenchers.socialradio.resourcemanager.common.LocalResourceType;
import com.abenchers.socialradio.resourcemanager.localstorage.persistence.LocalResourceDAO;

public class ResourceRemoveHandlerTest {

	private static final String RESOURCE = "resource";

	private ResourceRemoveHandler handler;

	private LocalResourceDAO localResourceDAOMock;

	@Before
	public void setUp() {
		handler = new ResourceRemoveHandler();

		localResourceDAOMock = Mockito.mock(LocalResourceDAO.class);
	}

	@Test
	public void shouldRemove() {

		LocalResourceModificationRequest request = new LocalResourceModificationRequest();
		request.setAction(LocalResourceAction.REMOVE);
		request.setLocalResourceType(LocalResourceType.DIRECTORY);
		request.setResource(RESOURCE);

		Mockito.when(localResourceDAOMock.removeResource(RESOURCE)).thenReturn(
				true);

		handler.setLocalResourceDAO(localResourceDAOMock);

		LocalResourceModificationResponse response = handler.process(request);

		Assert.assertEquals(ResponseStatus.SUCCESS, response.getStatus());
	}

	@Test
	public void shouldFiledRemoving() {

		LocalResourceModificationRequest request = new LocalResourceModificationRequest();
		request.setAction(LocalResourceAction.REMOVE);
		request.setLocalResourceType(LocalResourceType.DIRECTORY);
		request.setResource(RESOURCE);

		Mockito.when(localResourceDAOMock.removeResource(RESOURCE)).thenReturn(
				false);

		handler.setLocalResourceDAO(localResourceDAOMock);

		LocalResourceModificationResponse response = handler.process(request);

		Assert.assertEquals(ResponseStatus.FAIL, response.getStatus());
	}
}
