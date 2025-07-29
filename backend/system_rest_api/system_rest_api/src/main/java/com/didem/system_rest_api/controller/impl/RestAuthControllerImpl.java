package com.didem.system_rest_api.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.didem.system_rest_api.controller.IRestAuthController;
import com.didem.system_rest_api.dto.DtoUser;
import com.didem.system_rest_api.jwt.AuthRequest;
import com.didem.system_rest_api.jwt.AuthResponse;
import com.didem.system_rest_api.service.IAuthService;

import jakarta.validation.Valid;

@RestController

public class RestAuthControllerImpl implements IRestAuthController{

	@Autowired
	private IAuthService authService;
	
	@PostMapping("/register")
	@Override
	public DtoUser register(@Valid @RequestBody AuthRequest request) {
		return authService.register(request);
	}

	@PostMapping("/authenticate")
	@Override
	public AuthResponse authenticate(@Valid @RequestBody AuthRequest request) {
		
		return authService.authenticate(request);
	}

}
