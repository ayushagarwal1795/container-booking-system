package com.booking.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.beans.BookContainerRequest;
import com.booking.beans.CheckAvailabilityRequest;
import com.booking.service.ContainerServiceInterface;
import com.booking.utility.ContainerConstants;
import com.booking.utility.RequestValidator;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/bookings")
@Slf4j
public class ContainerBookingController {

	@Autowired
	private ContainerServiceInterface containerBookingService;

	@Autowired
	private RequestValidator requestValidator;

	@PostMapping(path = "/checkAvailability", produces = "application/json")
	public ResponseEntity checkAvailability(@Valid @RequestBody CheckAvailabilityRequest checkAvailabilityRequest) {
		try {
			if (requestValidator.checkContainerSize(checkAvailabilityRequest.getContainerSize())) {
				return ResponseEntity.ok().body(containerBookingService.getContainerAvailability(checkAvailabilityRequest));
			} else {
				return ResponseEntity.badRequest().body("Container size should be 20 or 40.");
			}
		} catch (Exception e) {
			log.error("Exception : ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ContainerConstants.ERROR_MESSAGE);
		}
	}

	@PostMapping(path = "/bookContainer", produces = "application/json")
	public ResponseEntity bookContainer(@Valid @RequestBody BookContainerRequest bookContainerRequest) {
		try {
			if (requestValidator.checkContainerSize(bookContainerRequest.getContainerSize())) {
				return ResponseEntity.ok().body(containerBookingService.bookContainer(bookContainerRequest));
			} else {
				return ResponseEntity.badRequest().body("Container size should be 20 or 40.");
			}
		} catch (Exception e) {
			log.error("Exception : ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ContainerConstants.ERROR_MESSAGE);
		}
	}

}
