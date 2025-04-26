//package com.gadgetzone.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf().disable() // Disable CSRF for simplicity; enable in production
//            .authorizeHttpRequests()
//                .requestMatchers("/api/users/register", "/api/users/login/**").permitAll() // public access
//                ..requestMatchers("/api/products/**").hasAuthority("ADMIN") // requires role 'ROLE_ADMIN'
//                .requestMatchers("/api/cart/**", "/api/orders/**").authenticated() // requires login
//                .anyRequest().authenticated()
//            .and()
//            .httpBasic() // for testing with Postman using Basic Auth
//            .and()
//            .formLogin(); // enables form login for web (optional)
//
//        return http.build();
//    }
//}




package com.gadgetzone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/api/users/register", "/api/users/login/**").permitAll()
	            .requestMatchers("/api/products/**").permitAll()
	            .requestMatchers("/api/cart/**", "/api/orders/**").permitAll()
	            .anyRequest().permitAll()
	        )
	        .httpBasic()
	        .and()
	        .formLogin();

	    return http.build();
	}

}

