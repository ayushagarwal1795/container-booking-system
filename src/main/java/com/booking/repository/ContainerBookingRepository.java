package com.booking.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.repository.CassandraRepository;

import com.booking.beans.BookContainer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ContainerBookingRepository implements CassandraRepository<BookContainer, Long> {
	
	

	public ContainerBookingRepository(CassandraOperations cassandraTemplate) {
		super();
		this.cassandraTemplate = cassandraTemplate;
	}

	@Autowired
	private CassandraOperations cassandraTemplate;

	public Long getmaxBookingReference() throws Exception {
		try {
			String selectQuery = "select max(booking_ref) from bookings;";
			Long maxBookingReference = cassandraTemplate.selectOne(selectQuery, Long.class);
			return maxBookingReference;
		} catch (Exception e) {
			log.error("Exception while fetching max booking reference number:", e);
			throw new Exception(e.getMessage());
		}
	}

}
