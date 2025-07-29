package com.didem.system_rest_api.config;

import com.didem.system_rest_api.model.User;
import com.didem.system_rest_api.repository.UserRepository;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    private final UserRepository userRepository;

    AppConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
    //username'i userdetails ile bulma
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				Optional<User> optional =  userRepository.findByUsername(username);
				if(optional.isPresent()) {
					return optional.get();
				}
				
				return null;
			}
		};
	}
	
	
	//DaoAuthenticationProvider sınıfında bulunan authenticate metodu sayesinde kulllanıcı adı-şifre kontrolü sağlanacak
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		
		return authenticationProvider;
	}

	//şifreleri hashleme
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
