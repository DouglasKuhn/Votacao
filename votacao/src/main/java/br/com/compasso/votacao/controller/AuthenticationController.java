package br.com.compasso.votacao.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.votacao.config.security.TokenService;
import br.com.compasso.votacao.controller.dto.TokenDto;
import br.com.compasso.votacao.controller.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	private final AuthenticationManager authManager;
	
	private final TokenService tokenService;

	public AuthenticationController(AuthenticationManager authManager, TokenService tokenService) {
		this.authManager = authManager;
		this.tokenService = tokenService;
	}

	@PostMapping
	public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid LoginForm form) {
		UsernamePasswordAuthenticationToken loginData = form.converter();
		
		try {
			Authentication authentication = authManager.authenticate(loginData);
			String token = tokenService.generateToken(authentication);
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
