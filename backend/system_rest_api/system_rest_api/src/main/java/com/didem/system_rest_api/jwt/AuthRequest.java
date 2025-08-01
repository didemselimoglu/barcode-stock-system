package com.didem.system_rest_api.jwt;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AuthRequest {
	
	@NotEmpty
	private String username;
	
	@NotEmpty
	private String password;
	

}
