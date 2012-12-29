package com.abenchers.socialradio.resourcemanager.localstorage;

import java.util.List;

import org.elasticsearch.common.collect.Lists;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("localResourceStorage")
@Scope(value = "singleton")
public class LocalResourceStorage {

	private List<String> resources;

	public LocalResourceStorage() {
		resources = Lists.newArrayList();
	}

	public List<String> getResources() {
		return resources;
	}

}
