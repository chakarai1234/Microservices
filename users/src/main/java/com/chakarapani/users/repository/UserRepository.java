package com.chakarapani.users.repository;


import com.chakarapani.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@SuppressWarnings("unused")
public interface UserRepository extends JpaRepository<Users, UUID> {

	Users findByUsername(String username);

	Users findByEmail(String email);

}