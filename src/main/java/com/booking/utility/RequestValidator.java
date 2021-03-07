package com.booking.utility;

import org.springframework.stereotype.Component;

@Component
public class RequestValidator {
	

	public boolean checkContainerSize(Integer containerSize) {
		if(containerSize==20 || containerSize==40) {
			return true;
		}
		return false;
	}

}
