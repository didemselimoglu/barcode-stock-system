package com.didem.system_rest_api.controller;

import com.didem.system_rest_api.dto.DtoUser;
import com.didem.system_rest_api.jwt.AuthRequest;
import com.didem.system_rest_api.jwt.AuthResponse;

public interface IRestAuthController {
	
	public DtoUser register(AuthRequest request);
	
	public AuthResponse authenticate(AuthRequest request);
	
	

}
