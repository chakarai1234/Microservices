package com.chakarapani.users.services;

import com.chakarapani.entity.Users;
import com.chakarapani.response.ResponseData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {


	ResponseData<List<Users>> getAllUsers(Map<String, String> headers);

	ResponseData<String> saveUser(Map<String,String> headers, Users user);


}
