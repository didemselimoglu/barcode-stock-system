package com.didem.system_rest_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.didem.system_rest_api.model.User;

@Repository

public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByUsername(String username);

}
