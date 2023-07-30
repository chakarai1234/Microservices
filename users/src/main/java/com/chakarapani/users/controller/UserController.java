package com.chakarapani.users.controller;

import com.chakarapani.entity.Users;
import com.chakarapani.response.ResponseData;
import com.chakarapani.users.services.UserServiceImpl;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.chakarapani.constants.Constants.xCorrId;

@SuppressWarnings("unused")
@RestController()
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:9090")
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	@GetMapping
	@Parameter(name = xCorrId, in = ParameterIn.HEADER, required = true, description = "Correlation id")
	public ResponseData<List<Users>> getAllUsers(@RequestHeader Map<String, String> headers) {
		return userService.getAllUsers(headers);
	}

	@PostMapping
	public ResponseData<String> saveUser(@RequestHeader Map<String, String> headers, @RequestBody Users user) {
		return userService.saveUser(headers, user);
	}


}
