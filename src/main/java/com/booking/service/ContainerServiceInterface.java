package com.booking.service;




import org.springframework.stereotype.Service;

import com.booking.beans.BookContainerRequest;
import com.booking.beans.BookContainerResponse;
import com.booking.beans.CheckAvailabilityRequest;
import com.booking.beans.CheckAvailabilityResponse;

import reactor.core.publisher.Mono;

@Service
public interface ContainerServiceInterface {

	Mono<CheckAvailabilityResponse> getContainerAvailability(CheckAvailabilityRequest checkAvailabilityRequest)throws Exception;

	Mono<BookContainerResponse> bookContainer(BookContainerRequest bookContainerRequest)throws Exception;

}
