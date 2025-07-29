package com.didem.system_rest_api.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.didem.system_rest_api.dto.DtoUser;
import com.didem.system_rest_api.jwt.AuthRequest;
import com.didem.system_rest_api.jwt.AuthResponse;
import com.didem.system_rest_api.jwt.JwtService;
import com.didem.system_rest_api.model.User;
import com.didem.system_rest_api.repository.UserRepository;
import com.didem.system_rest_api.service.IAuthService;

@Service
public class AuthServiceImpl  implements IAuthService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private JwtService jwtService;
	
	@Override
	public AuthResponse authenticate(AuthRequest request) {
		try {
			UsernamePasswordAuthenticationToken auth = 
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
			authenticationProvider.authenticate(auth);
			
			Optional<User> optionalUser =  userRepository.findByUsername(request.getUsername());
			String token = jwtService.generateToken(optionalUser.get());
			
			System.out.println("Giriş başarılı.");
			
			return new AuthResponse(token); 
			
		} catch (Exception e) {
			System.out.println("Kullanıcı adı veya şifre hatalı.");
		}
		return null;
	}

	@Override
	public DtoUser register(AuthRequest request) {
		
		DtoUser dto = new DtoUser();
		User user =new User();
	    
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword())); //hash'e çevirip kaydedecek
		
		User savedUser =  userRepository.save(user);
		BeanUtils.copyProperties(savedUser, dto);
		        
		return dto;
	}


}
