package amadeus.caseStudy.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import amadeus.caseStudy.core.requests.AuthenticationRequest;
import amadeus.caseStudy.core.requests.AuthenticationResponse;
import amadeus.caseStudy.core.requests.RegisterRequest;
import amadeus.caseStudy.core.services.AuthenticationService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationService service;
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
		return ResponseEntity.ok(service.register(request));
	}
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate (@RequestBody AuthenticationRequest request){
		return ResponseEntity.ok(service.authenticate(request));
	}
}
