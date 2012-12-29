package com.abenchers.socialradio.resourcemanager.localstorage;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.abenchers.socialradio.resourcemanager.common.LocalResourceAction;
import com.abenchers.socialradio.resourcemanager.common.LocalResourceModificationRequest;
import com.abenchers.socialradio.resourcemanager.common.LocalResourceModificationResponse;
import com.abenchers.socialradio.resourcemanager.localstorage.handler.ResourceAddHandler;

public class ResourceAdministrationServiceImplTest {

	private ResourceAdministrationServiceImpl resourceAdministrationServiceImpl;
	private ResourceHandlerFactory resourceHandlerFactoryMock;
	private LocalResourceModificationRequest request;

	@Before
	public void setUp() {
		resourceAdministrationServiceImpl = new ResourceAdministrationServiceImpl();
		resourceHandlerFactoryMock = Mockito.mock(ResourceHandlerFactory.class);
		request = new LocalResourceModificationRequest();
	}

	@Test
	public void shouldProcessResource() {

		ResourceAddHandler resourceAddHandler = Mockito
				.mock(ResourceAddHandler.class);
		request.setAction(LocalResourceAction.ADD);
		Mockito.when(resourceHandlerFactoryMock.getHandler(request.getAction()))
				.thenReturn(resourceAddHandler);
		Mockito.when(resourceAddHandler.process(request)).thenReturn(
				new LocalResourceModificationResponse());

		resourceAdministrationServiceImpl
				.setFileModificationHandlerFactory(resourceHandlerFactoryMock);
		resourceAdministrationServiceImpl.process(request);

		Mockito.verify(resourceHandlerFactoryMock).getHandler(
				request.getAction());
		Mockito.verify(resourceAddHandler).process(request);

	}

}
