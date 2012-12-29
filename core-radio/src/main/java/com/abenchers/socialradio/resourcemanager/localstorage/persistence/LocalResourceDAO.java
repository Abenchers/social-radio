package com.abenchers.socialradio.resourcemanager.localstorage.persistence;

import java.util.List;

public interface LocalResourceDAO {

	Boolean addResource(String resource);

	Boolean removeResource(String resource);

	List<String> listResources();

}
