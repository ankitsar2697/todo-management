package com.udemy.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return  new BCryptPasswordEncoder();
	}

	private UserDetailsService userDetailsService;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf((csrf) -> csrf.disable())
			.authorizeRequests((authorize) -> {
//				authorize.requestMatchers(HttpMethod.POST,"/api/**").hasRole("ADMIN");
//				authorize.requestMatchers(HttpMethod.PUT,"/api/**").hasRole("ADMIN");
//				authorize.requestMatchers(HttpMethod.DELETE,"/api/**").hasRole("ADMIN");
//				authorize.requestMatchers(HttpMethod.GET,"/api/**").hasAnyRole("ADMIN","USER");
//				authorize.requestMatchers(HttpMethod.PATCH,"/api/**").hasAnyRole("ADMIN","USER");
//				authorize.requestMatchers(HttpMethod.GET,"/api/**").permitAll();

				authorize.anyRequest().authenticated();
			}).httpBasic(Customizer.withDefaults());
		return http.build();
		
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
		
	}
	
	//create multiple users and configure them in inmemory authentication
	
	//as we r using db authentication som comment this inmemory database
//	@Bean
//	public UserDetailsService userDetailsService() {
//		
//		UserDetails ankit = User.builder()
//								.username("ankit").password(passwordEncoder().encode("ankit")).roles("USER").build() ;
//		
//		UserDetails admin = User.builder()
//								.username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").build();
//		
//		return new InMemoryUserDetailsManager(ankit,admin);
//		
//	}
}
