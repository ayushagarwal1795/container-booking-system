package com.booking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.booking.beans.BookContainer;
import com.booking.beans.BookContainerRequest;
import com.booking.beans.BookContainerRequest.ContainerType;
import com.booking.beans.BookContainerResponse;
import com.booking.beans.CheckAvailabilityRequest;
import com.booking.beans.CheckAvailabilityResponse;
import com.booking.beans.ExternalAvailabilityServiceResponse;
import com.booking.repository.ContainerAvailabilityExternalRepositoryImplementation;
import com.booking.repository.ContainerBookingRepository;
import com.booking.utility.ContainerConstants;

import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
public class ContainerServiceImplementationTest {
	
	@Mock
	private ContainerBookingRepository containerBookingRepository;
	
	@Mock
	private ContainerAvailabilityExternalRepositoryImplementation availabilityExternalRepositoryImplementation;
	
	@InjectMocks
	private ContainerServiceImplementation containerServiceImplementation;
	
	@Test
	public void getContainerAvailabilityTest() throws Exception {
		CheckAvailabilityRequest checkAvailabilityRequest=new CheckAvailabilityRequest();
		checkAvailabilityRequest.setContainerSize(20);
		checkAvailabilityRequest.setDestination("Lucknow");
		checkAvailabilityRequest.setOrigin("Pune City");
		checkAvailabilityRequest.setQuantity(12);
		ExternalAvailabilityServiceResponse externalAvailabilityServiceResponse=new ExternalAvailabilityServiceResponse(5);
		Mockito.doReturn(externalAvailabilityServiceResponse).when(availabilityExternalRepositoryImplementation).getExternalAvailabilityServiceResponse(Mockito.anyObject());
		Mono<CheckAvailabilityResponse> checkAvailabilityResponseActual=containerServiceImplementation.getContainerAvailability(checkAvailabilityRequest);
		assertEquals(true, checkAvailabilityResponseActual.block().getAvailable());
	}
	
	@Test
	public void getContainerAvailabilityTest_throwsException() throws Exception {
		CheckAvailabilityRequest checkAvailabilityRequest=new CheckAvailabilityRequest();
		checkAvailabilityRequest.setContainerSize(20);
		checkAvailabilityRequest.setDestination("Lucknow");
		checkAvailabilityRequest.setOrigin("Pune City");
		checkAvailabilityRequest.setQuantity(12);
		Mockito.doThrow(new Exception("Exception") {}).when(availabilityExternalRepositoryImplementation).getExternalAvailabilityServiceResponse(checkAvailabilityRequest);
		Assertions.assertThrows(Exception.class,()->containerServiceImplementation.getContainerAvailability(checkAvailabilityRequest));
	}
	
	@Test
	public void bookContainerTest() throws Exception {
		Mockito.doReturn(ContainerConstants.INITIAL_BOOKING_REF_NUMBER,ContainerConstants.INITIAL_BOOKING_REF_NUMBER).when(containerBookingRepository).getmaxBookingReference();
		Long bookingRef=containerBookingRepository.getmaxBookingReference();
		BookContainer bookContainer=new BookContainer();
		bookContainer.setBookingRef(bookingRef);
		bookContainer.setContainerSize(20);
		bookContainer.setDestination("Lucknow");
		bookContainer.setOrigin("Pune City");
		bookContainer.setQuantity(12);
		bookContainer.setTimestamp(Instant.now().toString());
		
		BookContainerRequest bookContainerRequest=new BookContainerRequest();
		
		bookContainerRequest.setContainerSize(20);
		bookContainerRequest.setContainerType(ContainerType.DRY);
		bookContainerRequest.setDestination("Lucknow");
		bookContainerRequest.setOrigin("Pune City");
		bookContainerRequest.setQuantity(12);
		bookContainerRequest.setTimestamp(Instant.now().toString());
		bookContainerRequest.setTimestamp(Instant.now().toString());
		
		
		Mockito.doReturn(bookContainer).when(containerBookingRepository).save(Mockito.anyObject());
		Mono<BookContainerResponse> bookContainerResponse=containerServiceImplementation.bookContainer(bookContainerRequest);
		assertEquals(ContainerConstants.INITIAL_BOOKING_REF_NUMBER.toString(), bookContainerResponse.block().getBookingRef());
	}
	
	@Test
	public void bookContainerTest_throwsException() throws Exception {
	BookContainerRequest bookContainerRequest=new BookContainerRequest();
		
		bookContainerRequest.setContainerSize(20);
		bookContainerRequest.setContainerType(ContainerType.DRY);
		bookContainerRequest.setDestination("Lucknow");
		bookContainerRequest.setOrigin("Pune City");
		bookContainerRequest.setQuantity(12);
		bookContainerRequest.setTimestamp(Instant.now().toString());
		bookContainerRequest.setTimestamp(Instant.now().toString());
		
		Mockito.doThrow(new Exception("Exception") {}).when(containerBookingRepository).getmaxBookingReference();
		Assertions.assertThrows(Exception.class,()->containerServiceImplementation.bookContainer(bookContainerRequest));
	}
	

}
