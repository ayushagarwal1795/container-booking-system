package com.booking.repository;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.booking.beans.CheckAvailabilityRequest;
import com.booking.beans.ExternalAvailabilityServiceResponse;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Repository
@Slf4j
public class ContainerAvailabilityExternalRepositoryImplementation implements ContainerAvailabilityExternalRepositoryInterface{

	@Value("${external.availability.url}")
	private String externalURL;
	
	private RestTemplate restTemplate ;
	
	@Override
	public ExternalAvailabilityServiceResponse getExternalAvailabilityServiceResponse(CheckAvailabilityRequest checkAvailabilityRequest) throws Exception {
		try {
			log.info("Calling exteranl service for checking availability");
			
			/* Code for calling External Service for checking availability
			 * 
			restTemplate = new RestTemplate();
			HttpEntity<CheckAvailabilityRequest> request = new HttpEntity<CheckAvailabilityRequest>(checkAvailabilityRequest);

			ResponseEntity<ExternalAvailabilityServiceResponse> response = restTemplate.exchange(
					UriComponentsBuilder.fromUriString(externalURL).build().encode().toUri(), HttpMethod.POST,
					request, ExternalAvailabilityServiceResponse.class);

			return response.getBody();
			*/
			
			
			  ExternalAvailabilityServiceResponse externalAvailabilityServiceResponse= new ExternalAvailabilityServiceResponse();
			  externalAvailabilityServiceResponse.setAvailableSpace(5); 
			  return externalAvailabilityServiceResponse;
			 
			
		} catch (Exception e) {
			log.error("Encountered Exception: ",e);
			throw new Exception(e.getMessage());
		}
	}

}
