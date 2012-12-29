package com.abenchers.socialradio.resourcemanager.localstorage;

import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;
import com.google.common.io.Files;

@Component("aceptedFileExtensionPredicate")
public class AceptedFileExtensionPredicate implements Predicate<String> {

	private List<String> extensionsList;

	@Override
	public boolean apply(String input) {
		final String fileExtension = Files.getFileExtension(input);
		if (extensionsList.contains(fileExtension)) {
			return true;
		}
		return false;
	}

	public void setExtensionsList(final List<String> extensionsList) {
		this.extensionsList = extensionsList;
	}

}
