package com.cifuentes.app.api.securitys;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		// Configuración de seguridad
		 http
         .authorizeRequests()
             .anyRequest().authenticated() // Todas las solicitudes requieren autenticación
             .and()
         .httpBasic() // Habilita Basic Authentication
             .authenticationEntryPoint((request, response, authException) -> handleAuthError(response)) // Manejo de error personalizado
             .and()
         .csrf().disable(); // Deshabilitar CSRF para facilitar pruebas con Postman, etc.

     return http.build();
	}
	
    private void handleAuthError(HttpServletResponse response) throws IOException {
        // Configura la respuesta cuando hay error de autenticación
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Código de estado 401
        response.setContentType("application/json");
        response.getWriter().write("{\"mensaje\": \"Error: Autenticación requerida o credenciales incorrectas\"}");
    }
	
	@Bean
	public UserDetailsService userDetailsService() {
        // Definir usuarios en memoria con roles
        var user1 = User.withUsername("admin")
                .password("{noop}password") // {noop} indica que no estamos usando encriptación
                .roles("ADMIN")
                .build();
        
        var user2 = User.withUsername("user")
                .password("{noop}password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }
}
