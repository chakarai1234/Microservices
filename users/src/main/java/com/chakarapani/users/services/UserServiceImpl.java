package com.chakarapani.users.services;

import com.chakarapani.entity.Users;
import com.chakarapani.enums.Message;
import com.chakarapani.exception.CorrelationMissingException;
import com.chakarapani.response.ResponseData;
import com.chakarapani.users.exception.EmailAlreadyExistsException;
import com.chakarapani.users.exception.UsernameAlreadyExistsException;
import com.chakarapani.users.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import net.logstash.logback.argument.StructuredArguments;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.chakarapani.constants.Constants.xCorrId;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ObjectMapper objectMapper;


	@Override
	public ResponseData<List<Users>> getAllUsers(@NotNull Map<String, String> headers) {

		if (headers.get(xCorrId) == null) {
			CorrelationMissingException exception = new CorrelationMissingException(xCorrId + " is missing");
			log.error(exception.getMessage(), StructuredArguments.entries(headers));
			throw exception;
		} else {
			ResponseData<List<Users>> responseData =
					new ResponseData<>(Message.SUCCESS, headers, HttpStatus.OK, userRepository.findAll());
			try {
				log.info(objectMapper.writeValueAsString(responseData), StructuredArguments.entries(headers));
			} catch (JsonProcessingException e) {
				throw new RuntimeException(e);
			}
			return responseData;
		}

	}

	@Override
	public ResponseData<String> saveUser(Map<String, String> headers, Users user) {
		if (headers.get(xCorrId) == null) {
			CorrelationMissingException exception = new CorrelationMissingException(xCorrId + " is missing");
			log.error(exception.getMessage(), StructuredArguments.entries(headers));
			throw exception;
		} else {
			try {
				userRepository.save(user);
				log.info("User saved" + user);
				ResponseData<String> responseData =
						new ResponseData<>(Message.SUCCESS, headers, HttpStatus.OK, "User successfully saved");
				log.info(objectMapper.writeValueAsString(responseData), StructuredArguments.entries(headers));
				return responseData;
			} catch (Exception e) {
				if (e.getMessage().contains("EMAIL")) {
					log.error(e.getMessage(), StructuredArguments.entries(headers));
					throw new EmailAlreadyExistsException("Email already exists");
				} else if (e.getMessage().contains("USER")) {
					log.error(e.getMessage(), StructuredArguments.entries(headers));
					throw new UsernameAlreadyExistsException("Username already exists");
				} else {
					log.error(e.getMessage(), StructuredArguments.entries(headers));
					throw new RuntimeException(e);
				}
			}
		}
	}
}
