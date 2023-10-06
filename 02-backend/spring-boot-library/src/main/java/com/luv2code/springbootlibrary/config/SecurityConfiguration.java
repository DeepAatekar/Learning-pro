package com.luv2code.springbootlibrary.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
//
//import org.springframework.boot.autoconfigure.session.RedisSessionProperties.ConfigureAction;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

import com.luv2code.springbootlibrary.utils.ExtractJWT;
import com.okta.spring.boot.oauth.Okta;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.accept.ContentNegotiationStrategy;
//import org.springframework.web.accept.HeaderContentNegotiationStrategy;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import com.okta.spring.boot.oauth.Okta;
//
@Configuration
//@EnableWebSecurity
public class SecurityConfiguration
{
	private static final Logger logger = LoggerFactory.getLogger(ExtractJWT .class);
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		//Disable Cross Site Request Forgery
		http.csrf().disable();
		
		http.authorizeHttpRequests(configurer -> configurer.requestMatchers("/api/books/secure/**")
				.authenticated().anyRequest().permitAll())
	
//		http.authorizeRequests(configurer ->
//        configurer
//                .requestMatchers("/api/books/secure/**")
//                .authenticated())
		.oauth2ResourceServer()
		.jwt();
//		
//		
		//Add CORS filters
		http.cors();
//		
//		
		//Add content negotiation strategy
		http.setSharedObject(ContentNegotiationStrategy.class, new HeaderContentNegotiationStrategy());
//		
//		
		//Force a non-empty resource body for 401's to make the response friendly
		//Okta.configureResourceServer401ResponseBody(http);
//		
		return http.build();
	
		
//		
	}
//	
//	
////		@Bean
////	    public CorsConfigurationSource corsConfigurationSource() {
////	        CorsConfiguration configuration = new CorsConfiguration();
////	        configuration.addAllowedOrigin("http://localhost:3000"); // Adjust the allowed origin(s) as needed
////	        configuration.addAllowedMethod("*");
////	        configuration.addAllowedHeader("*");
////	        configuration.setAllowCredentials(true);
////
////	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////	        source.registerCorsConfiguration("/api/**", configuration); // Adjust the path pattern as needed
////
////	        return source;
////}
	
	
}
