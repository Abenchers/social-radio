package com.abenchers.socialradio.resourcemanager.localstorage;

import com.abenchers.socialradio.resourcemanager.common.LocalResourceModificationRequest;
import com.abenchers.socialradio.resourcemanager.common.LocalResourceModificationResponse;

public interface ResourceAdministrationService {

	LocalResourceModificationResponse process(
			LocalResourceModificationRequest request);

}
