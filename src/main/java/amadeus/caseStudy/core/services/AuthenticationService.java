package amadeus.caseStudy.core.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import amadeus.caseStudy.core.dataAccess.UserDao;
import amadeus.caseStudy.core.entities.User;
import amadeus.caseStudy.core.jwt.JwtService;
import amadeus.caseStudy.core.jwt.Role;
import amadeus.caseStudy.core.requests.AuthenticationRequest;
import amadeus.caseStudy.core.requests.AuthenticationResponse;
import amadeus.caseStudy.core.requests.RegisterRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserDao userDao;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authManager;
	public AuthenticationResponse register(RegisterRequest register) {
		var user = User.builder()
				.firstname(register.getFirstname())
				.lastname(register.getLastname())
				.email(register.getEmail())
				.password(passwordEncoder.encode(register.getPassword()))
				.role(Role.USER).build();
		userDao.save(user);
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(jwtToken).build();

	}

	public AuthenticationResponse authenticate (AuthenticationRequest request) {
		authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
		var user = userDao.findByEmail(request.getEmail()).orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(jwtToken).build();

	}
}
