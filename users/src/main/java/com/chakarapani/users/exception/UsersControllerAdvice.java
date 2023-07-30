package com.chakarapani.users.exception;

import com.chakarapani.enums.Message;
import com.chakarapani.exception.CorrelationMissingException;
import com.chakarapani.response.ResponseData;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class UsersControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CorrelationMissingException.class)
	public ResponseData<String> handleCorrelationMissingException(CorrelationMissingException exception) {
		return new ResponseData<>(Message.FAILURE, HttpStatus.BAD_REQUEST, exception.getMessage());
	}

	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseData<String> handleEmailAlreadyExistsException(EmailAlreadyExistsException exception) {
		return new ResponseData<>(Message.FAILURE, HttpStatus.BAD_REQUEST, exception.getMessage());
	}

	@ExceptionHandler(UsernameAlreadyExistsException.class)
	public ResponseData<String> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException exception) {
		return new ResponseData<>(Message.FAILURE, HttpStatus.BAD_REQUEST, exception.getMessage());
	}

}
