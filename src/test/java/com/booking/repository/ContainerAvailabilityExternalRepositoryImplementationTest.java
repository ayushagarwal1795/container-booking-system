package com.booking.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.booking.beans.CheckAvailabilityRequest;
import com.booking.beans.ExternalAvailabilityServiceResponse;

@ExtendWith(MockitoExtension.class)
public class ContainerAvailabilityExternalRepositoryImplementationTest {

	@Mock
	private ContainerAvailabilityExternalRepositoryImplementation containerAvailabilityExternalRepositoryImplementation;

	@Test
	public void getExternalAvailabilityServiceResponseTest() throws Exception {

		ExternalAvailabilityServiceResponse externalAvailabilityServiceResponse = new ExternalAvailabilityServiceResponse();
		externalAvailabilityServiceResponse.setAvailableSpace(5);
		CheckAvailabilityRequest checkAvailabilityRequest=new CheckAvailabilityRequest();
		checkAvailabilityRequest.setContainerSize(20);
		checkAvailabilityRequest.setDestination("Lucknow");
		checkAvailabilityRequest.setOrigin("Pune City");
		checkAvailabilityRequest.setQuantity(12);
		Mockito.doReturn(externalAvailabilityServiceResponse).when(containerAvailabilityExternalRepositoryImplementation).getExternalAvailabilityServiceResponse(checkAvailabilityRequest);
		ExternalAvailabilityServiceResponse externalAvailabilityServiceResponseActual=containerAvailabilityExternalRepositoryImplementation.getExternalAvailabilityServiceResponse(checkAvailabilityRequest);
		assertEquals(5, externalAvailabilityServiceResponseActual.getAvailableSpace());
	}
	
	@Test
	public void getExternalAvailabilityServiceResponseTest_throwsException() throws Exception {
		CheckAvailabilityRequest checkAvailabilityRequest=new CheckAvailabilityRequest();
		checkAvailabilityRequest.setContainerSize(20);
		checkAvailabilityRequest.setDestination("Lucknow");
		checkAvailabilityRequest.setOrigin("Pune City");
		checkAvailabilityRequest.setQuantity(12);
		Mockito.doThrow(new Exception("Exception") {}).when(containerAvailabilityExternalRepositoryImplementation).getExternalAvailabilityServiceResponse(checkAvailabilityRequest);
		Assertions.assertThrows(Exception.class,()->containerAvailabilityExternalRepositoryImplementation.getExternalAvailabilityServiceResponse(checkAvailabilityRequest));
	}

}
