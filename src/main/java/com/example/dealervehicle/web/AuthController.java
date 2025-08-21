package com.example.dealervehicle.web;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.dealervehicle.security.JwtService;

import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private final JwtService jwtService;

	public AuthController(JwtService jwtService) {
		this.jwtService = jwtService;
	}

	@PostMapping("/token")
	@ResponseStatus(HttpStatus.OK)
	public Map<String, String> token(@RequestParam("username") @NotBlank String username) {
		String token = jwtService.generateToken(username);
		return Map.of("token", token);
	}
}

