package app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // 🔒 Desativa CSRF (necessário para APIs REST sem sessão)
            .csrf(csrf -> csrf.disable())

            // 🌍 Ativa CORS com nossa configuração abaixo
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))

            // 🚫 Sem sessões — API stateless
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // ✅ Permissões de endpoints
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers("/api/**").permitAll()
                .anyRequest().permitAll() // 🔥 tudo liberado por enquanto
            )

            // 🧱 Evita bloqueios de frame (como o console H2)
            .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }

    // 🌐 Configuração global de CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // URL do front-end Angular
        config.setAllowedOrigins(List.of("http://localhost:4200"));

        // Métodos permitidos
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // Cabeçalhos permitidos
        config.setAllowedHeaders(List.of("*"));

        // Permite envio de cookies/autenticação
        config.setAllowCredentials(true);

        // Cabeçalhos expostos no navegador
        config.setExposedHeaders(List.of("Authorization", "Content-Type"));

        // Cache do CORS (1 hora)
        config.setMaxAge(3600L);

        // Aplica a configuração a todos os endpoints
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
