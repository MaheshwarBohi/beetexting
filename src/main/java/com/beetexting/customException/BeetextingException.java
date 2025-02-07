package com.beetexting.customException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BeetextingException extends RuntimeException {
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;
	
	private String status;
	
	public BeetextingException(String message, String status) {
		super(message);
		this.status = status;
	}

}
