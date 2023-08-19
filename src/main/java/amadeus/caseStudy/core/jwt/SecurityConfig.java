package amadeus.caseStudy.core.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;


import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
	
	private final JwtAuthenticationFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers("api/authentication/**")
		.permitAll()
		
		.requestMatchers(GET,"api/flights/**").permitAll()
		.requestMatchers(GET,"api/airports/**").permitAll()
		.requestMatchers(POST,"api/flights/**").hasAnyAuthority("ADMIN")
		.requestMatchers("/api/mock/**").permitAll()
		.requestMatchers(GET,"/v3/api-docs/**","/swagger-ui/**","/swagger-ui.html").permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

}
