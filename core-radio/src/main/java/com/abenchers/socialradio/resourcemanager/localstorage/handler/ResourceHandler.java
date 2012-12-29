package com.abenchers.socialradio.resourcemanager.localstorage.handler;

import java.io.File;
import java.util.List;

import org.elasticsearch.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;

import com.abenchers.socialradio.resourcemanager.common.LocalResourceModificationRequest;
import com.abenchers.socialradio.resourcemanager.common.LocalResourceModificationResponse;
import com.abenchers.socialradio.resourcemanager.localstorage.AceptedFileExtensionPredicate;
import com.abenchers.socialradio.resourcemanager.localstorage.persistence.LocalResourceDAO;
import com.google.common.collect.Iterables;

public abstract class ResourceHandler {

	@Autowired
	private LocalResourceDAO localResourceDAO;

	@Autowired
	private AceptedFileExtensionPredicate aceptedFileExtensionPredicate;

	public abstract LocalResourceModificationResponse process(
			LocalResourceModificationRequest request);

	protected List<String> filterAceptedExtensions(File resource) {

		List<String> fileList = (resource.isFile()) ? Lists
				.newArrayList(resource.getAbsolutePath())
				: getFullPathFilesList(resource.list(), resource);

		List<String> filteredList = Lists.newArrayList(Iterables.filter(
				fileList, aceptedFileExtensionPredicate));
		return filteredList;
	}

	private List<String> getFullPathFilesList(String[] list, File resource) {
		List<String> fullPathList = Lists.newArrayList();
		for (String file : list) {
			fullPathList.add(trasformPathToSystemPath(resource
					.getAbsolutePath() +"\\" + file));
		}
		return fullPathList;
	}

	private String trasformPathToSystemPath(String path) {
		path.replace(File.pathSeparator, File.pathSeparator+File.pathSeparator);
		return path;
	}

	public void setAceptedFileExtensionPredicate(
			AceptedFileExtensionPredicate aceptedFileExtensionPredicate) {
		this.aceptedFileExtensionPredicate = aceptedFileExtensionPredicate;
	}

	public LocalResourceDAO getLocalResourceDAO() {
		return localResourceDAO;
	}

	public void setLocalResourceDAO(LocalResourceDAO localResourceDAO) {
		this.localResourceDAO = localResourceDAO;
	}

}
