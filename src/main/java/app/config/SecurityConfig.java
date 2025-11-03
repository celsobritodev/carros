package app.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig  {
	

	@Autowired
	private JwtAuthenticationFilter jwtAuthFilter;

	@Autowired
	private AuthenticationProvider authenticationProvider;


	///////////////////////////////////////////////////////

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(AbstractHttpConfigurer::disable)
	        .cors(cors -> cors.configurationSource(request -> {
	            var config = new org.springframework.web.cors.CorsConfiguration();
	            config.setAllowCredentials(true);
	            config.setAllowedOriginPatterns(List.of("http://localhost:4200")); // Angular local
	            config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	            config.setAllowedHeaders(List.of("Authorization", "Content-Type", "Accept"));
	            config.setExposedHeaders(List.of("Authorization")); // se precisar ler o token no front
	            config.setMaxAge(3600L);
	            return config;
	        }))
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/api/login").permitAll()
	        //    .requestMatchers("/api/carro/listAll").hasAnyRole("ADMIN") <= OUTRA FORMA DE LIBERAR ENDPOINTS
	            .anyRequest().authenticated()
	        )
	        .authenticationProvider(authenticationProvider)
	        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

	    return http.build();
	}

	///////////////////////////////////////////////////////



	
	


}
