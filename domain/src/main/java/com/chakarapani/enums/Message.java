package com.chakarapani.enums;

import lombok.Getter;

@Getter
public enum Message {
	SUCCESS("SUCCESS"), FAILURE("FAILURE");

	private final String message;

	Message(String message) {
		this.message = message;
	}

}