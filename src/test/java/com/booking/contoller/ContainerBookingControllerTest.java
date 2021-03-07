package com.booking.contoller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.booking.beans.BookContainerRequest;
import com.booking.beans.CheckAvailabilityRequest;
import com.booking.service.ContainerServiceImplementation;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ContainerBookingControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	private ContainerServiceImplementation containerServiceImplementation;
	
	@Test
	public void shouldReturnBadRequestForWrongContainerSize() throws Exception {
		CheckAvailabilityRequest checkAvailabilityRequest=new CheckAvailabilityRequest();
		checkAvailabilityRequest.setContainerSize(21);
		checkAvailabilityRequest.setDestination("Lucknow");
		checkAvailabilityRequest.setOrigin("Pune City");
		checkAvailabilityRequest.setQuantity(12);
		String request= new ObjectMapper().writeValueAsString(checkAvailabilityRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/bookings/checkAvailability").contentType(MediaType.APPLICATION_JSON).content(request))
		.andDo(MockMvcResultHandlers.print()).andExpect(status().isBadRequest());
	}
	
	@Test
	public void shouldReturnBadRequestForWrongDestinationLength() throws Exception {
		CheckAvailabilityRequest checkAvailabilityRequest=new CheckAvailabilityRequest();
		checkAvailabilityRequest.setContainerSize(20);
		checkAvailabilityRequest.setDestination("L");
		checkAvailabilityRequest.setOrigin("Pune City");
		checkAvailabilityRequest.setQuantity(12);
		String request= new ObjectMapper().writeValueAsString(checkAvailabilityRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/bookings/checkAvailability").contentType(MediaType.APPLICATION_JSON).content(request))
		.andDo(MockMvcResultHandlers.print()).andExpect(status().isBadRequest());
	}
	
	@Test
	public void shouldReturnBadRequestForWrongOriginLength() throws Exception {
		CheckAvailabilityRequest checkAvailabilityRequest=new CheckAvailabilityRequest();
		checkAvailabilityRequest.setContainerSize(20);
		checkAvailabilityRequest.setDestination("Lucknow");
		checkAvailabilityRequest.setOrigin("P");
		checkAvailabilityRequest.setQuantity(12);
		String request= new ObjectMapper().writeValueAsString(checkAvailabilityRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/bookings/checkAvailability").contentType(MediaType.APPLICATION_JSON).content(request))
		.andDo(MockMvcResultHandlers.print()).andExpect(status().isBadRequest());
	}
	
	@Test
	public void shouldReturnBadRequestForWrongQuantity() throws Exception {
		CheckAvailabilityRequest checkAvailabilityRequest=new CheckAvailabilityRequest();
		checkAvailabilityRequest.setContainerSize(20);
		checkAvailabilityRequest.setDestination("Lucknow");
		checkAvailabilityRequest.setOrigin("Pune City");
		checkAvailabilityRequest.setQuantity(-1);
		String request= new ObjectMapper().writeValueAsString(checkAvailabilityRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/bookings/checkAvailability").contentType(MediaType.APPLICATION_JSON).content(request))
		.andDo(MockMvcResultHandlers.print()).andExpect(status().isBadRequest());
	}
	
	@Test
	public void shouldReturnOkForValidRequest() throws Exception {
		CheckAvailabilityRequest checkAvailabilityRequest=new CheckAvailabilityRequest();
		checkAvailabilityRequest.setContainerSize(20);
		checkAvailabilityRequest.setDestination("Lucknow");
		checkAvailabilityRequest.setOrigin("Pune City");
		checkAvailabilityRequest.setQuantity(12);
		String request= new ObjectMapper().writeValueAsString(checkAvailabilityRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/bookings/checkAvailability").contentType(MediaType.APPLICATION_JSON).content(request))
		.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
	}
	
	@Test
	public void shouldBookConatinerForValidRequest() throws Exception {
		BookContainerRequest bookContainerRequest=new BookContainerRequest();
		bookContainerRequest.setContainerSize(20);
		bookContainerRequest.setDestination("Lucknow");
		bookContainerRequest.setOrigin("Pune City");
		bookContainerRequest.setQuantity(12);
		bookContainerRequest.setTimestamp(Instant.now().toString());
		String request= new ObjectMapper().writeValueAsString(bookContainerRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/bookings/bookContainer").contentType(MediaType.APPLICATION_JSON).content(request))
		.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
	}
	
}
