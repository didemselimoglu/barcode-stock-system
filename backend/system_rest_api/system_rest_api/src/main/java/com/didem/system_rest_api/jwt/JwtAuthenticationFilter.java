package com.didem.system_rest_api.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{ //her gelen istek bu katmana düşecek
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		String header = request.getHeader("Authorization");
		
		//header boşsa controller'a düşemez, geriye döner
		if (header==null) { //|| !header.startsWith("Bearer "
			filterChain.doFilter(request, response);
			return;
		}
		
	
		String token = header.substring(7);
		//username kullanarak gelen tokenı çözüyorum
		try {
			
			String username = jwtService.getUsernameByToken(token);
			
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				UserDetails userDetails =  userDetailsService.loadUserByUsername(username);
				
				//token süresı dolmadıysa kullanıcı içeri girebilir
				if(userDetails!=null && jwtService.isTokenExpired(token)) {
					
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					
					//authentication.setDetails(userDetails);
					
					SecurityContextHolder.getContext().setAuthentication(authentication);
					
					}
				}
		} 
		
		catch (ExpiredJwtException e) {
			System.out.println("Token süresi dolmuştur : " + e.getMessage());
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
		}
		
		catch (Exception e) {
			System.out.println("Genel bir hata oluştu : " + e.getMessage());
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
		}
		
		filterChain.doFilter(request, response);
		
		}
}
