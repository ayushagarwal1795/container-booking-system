package com.booking.beans;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(value = "bookings")
public class BookContainer {
	
	@PrimaryKey	
	@Column(value = "booking_ref")
	private Long bookingRef;

	@Column(value = "container_size")
	private Integer containerSize;

	@Column(value = "container_type")
	public String containerType;

	@Column(value = "origin")
	private String origin;

	@Column(value = "destination")
	private String destination;

	@Column(value = "quantity")
	private Integer quantity;
	
    @Column(value = "timestamp")
	private String timestamp;
}
