package com.didem.system_rest_api.service;

import com.didem.system_rest_api.dto.DtoUser;
import com.didem.system_rest_api.jwt.AuthRequest;
import com.didem.system_rest_api.jwt.AuthResponse;

public interface IAuthService {
	
	public DtoUser register(AuthRequest authRequest);
	
	public AuthResponse authenticate(AuthRequest request );

}
