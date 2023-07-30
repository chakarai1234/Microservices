package com.chakarapani.response;


import com.chakarapani.enums.Message;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseData<T> {

	@JsonProperty("timestamp")
	private final String timeStamp =
			LocalDateTime.now(ZoneId.of("Singapore")).format(DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm:ss"));

	@JsonProperty("x-correlation-id")
	private String xCorrId = null;

	@JsonProperty("message")
	private String message;

	@JsonProperty("status")
	private HttpStatus httpStatus;

	@JsonProperty("status_code")
	private int statusCode;

	private T data;

	public ResponseData(@NotNull Message message, @NotNull Map<String, String> headers, @NotNull HttpStatus httpStatus,
	                    T data) {
		this.xCorrId = headers.get("x-correlation-id");
		this.message = message.getMessage();
		this.httpStatus = httpStatus;
		this.statusCode = httpStatus.value();
		this.data = data;
	}

	public ResponseData(@NotNull Message message, @NotNull HttpStatus httpStatus, T data) {
		this.message = message.getMessage();
		this.httpStatus = httpStatus;
		this.statusCode = httpStatus.value();
		this.data = data;
	}

}
