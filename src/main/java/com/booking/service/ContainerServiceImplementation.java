package com.booking.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.beans.BookContainer;
import com.booking.beans.BookContainerRequest;
import com.booking.beans.BookContainerResponse;
import com.booking.beans.CheckAvailabilityRequest;
import com.booking.beans.CheckAvailabilityResponse;
import com.booking.beans.ExternalAvailabilityServiceResponse;
import com.booking.repository.ContainerAvailabilityExternalRepositoryInterface;
import com.booking.repository.ContainerBookingRepository;
import com.booking.utility.ContainerConstants;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ContainerServiceImplementation implements ContainerServiceInterface {

	@Autowired
	private ContainerAvailabilityExternalRepositoryInterface containerAvailabilityExternalRepository;
	
	@Autowired
	private ContainerBookingRepository containerBookingRepository;

	@Override
	public Mono<CheckAvailabilityResponse> getContainerAvailability(CheckAvailabilityRequest checkAvailabilityRequest)throws Exception {
		Mono<CheckAvailabilityResponse> checkAvailabilityResponseMono = null;
		try {
			ExternalAvailabilityServiceResponse externalAvailabilityServiceResponse = containerAvailabilityExternalRepository.getExternalAvailabilityServiceResponse(checkAvailabilityRequest);
			CheckAvailabilityResponse checkAvailabilityResponse = new CheckAvailabilityResponse();
			if (externalAvailabilityServiceResponse!=null) {
				int availableSpace = externalAvailabilityServiceResponse.getAvailableSpace();
				checkAvailabilityResponse.setAvailable(availableSpace == 0 ? false : true);
				checkAvailabilityResponseMono = Mono.just(checkAvailabilityResponse);
				return checkAvailabilityResponseMono;
			}
		} catch (Exception e) {
			log.error("Encountered Exception: ",e);
			throw new Exception(e.getMessage());
		}

		return checkAvailabilityResponseMono;
	}

	@Override
	public Mono<BookContainerResponse> bookContainer(BookContainerRequest bookContainerRequest) throws Exception {
		Mono<BookContainerResponse> bookedContainerResponseMono = null;
		try {

			BookContainer bookContainer = new BookContainer();
			BookContainerResponse bookContainerResponse=new BookContainerResponse();
			Long maxBookingRef=containerBookingRepository.getmaxBookingReference();
			Long bookingRef=(maxBookingRef==null)?ContainerConstants.INITIAL_BOOKING_REF_NUMBER:(maxBookingRef+1);
			bookContainer.setBookingRef(bookingRef);
			bookContainer.setContainerSize(bookContainerRequest.getContainerSize());
			bookContainer.setContainerType(bookContainerRequest.getContainerType().toString());
			bookContainer.setOrigin(bookContainerRequest.getOrigin());
			bookContainer.setDestination(bookContainerRequest.getDestination());
			bookContainer.setQuantity(bookContainerRequest.getQuantity());
			bookContainer.setTimestamp(bookContainerRequest.getTimestamp());
			BookContainer bookedContainer = containerBookingRepository.save(bookContainer);
			
			if(bookedContainer!=null) {
				bookContainerResponse.setBookingRef(bookedContainer.getBookingRef().toString());
				bookedContainerResponseMono = Mono.just(bookContainerResponse);
				return bookedContainerResponseMono;
			}
			
		} catch (Exception e) {
			log.error("Exception : ", e);
			throw new Exception(e.getMessage());
		}
		return bookedContainerResponseMono;
	}

}
