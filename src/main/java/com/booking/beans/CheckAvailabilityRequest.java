package com.booking.beans;

import com.booking.beans.BookContainerRequest.ContainerType;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.validation.constraints.Max;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CheckAvailabilityRequest {
	
	@JsonInclude
	private Integer containerSize;
	
	@JsonInclude
	private ContainerType containerType;
	
	@JsonInclude
	@Size(min=5,max=20,message = "Origin name should have charachters between 5 to 20.")
	private String origin;
	
	@JsonInclude
	@Size(min=5,max=20,message = "Destination name should have charachters between 5 to 20.")
	private String destination;
	
	@JsonInclude
	@Min(value = 1,message = "Quantity should be between 1 and 100.")
	@Max(value = 100,message = "Quantity should be between 1 and 100.")
	private Integer quantity;
		
	public enum containerType{
		DRY,
		REEFER
	}
}
