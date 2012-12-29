package com.abenchers.socialradio.resourcemanager.localstorage.handler;

import java.util.List;

import junit.framework.Assert;

import org.elasticsearch.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.abenchers.socialradio.resourcemanager.common.LocalResourceAction;
import com.abenchers.socialradio.resourcemanager.common.LocalResourceModificationRequest;
import com.abenchers.socialradio.resourcemanager.common.LocalResourceModificationResponse;
import com.abenchers.socialradio.resourcemanager.localstorage.persistence.LocalResourceDAO;

public class ResourceListHandlerTest {

	private ResourceListHandler handler;

	private LocalResourceDAO localResourceDAOMock;

	@Before
	public void setUp() {
		handler = new ResourceListHandler();

		localResourceDAOMock = Mockito.mock(LocalResourceDAO.class);
	}

	@Test
	public void shouldList() {

		List<String> resourcesList = Lists.newArrayList();
		resourcesList.add("firstResource");
		resourcesList.add("secondResource");
		resourcesList.add("thirtResource");

		LocalResourceModificationRequest request = new LocalResourceModificationRequest();
		request.setAction(LocalResourceAction.LIST);

		Mockito.when(localResourceDAOMock.listResources()).thenReturn(
				resourcesList);

		handler.setLocalResourceDAO(localResourceDAOMock);

		LocalResourceModificationResponse response = handler.process(request);

		Assert.assertEquals(3, response.getResourceList().size());
	}
}
