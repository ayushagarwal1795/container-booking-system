package com.booking.repository;

import java.util.Optional;

import com.booking.beans.CheckAvailabilityRequest;
import com.booking.beans.ExternalAvailabilityServiceResponse;

public interface ContainerAvailabilityExternalRepositoryInterface {

	public ExternalAvailabilityServiceResponse getExternalAvailabilityServiceResponse(CheckAvailabilityRequest checkAvailabilityRequest)throws Exception;
	

}
