package com.darla.configurations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigLogin {

	@Autowired
	private JWTFilter jwtFilter;
	
	@Bean
	SecurityFilterChain
	m(HttpSecurity security) throws Exception {
		return security
				.csrf(customizer -> customizer.disable())
				.authorizeHttpRequests(req -> req
						.requestMatchers("login", "regester-user")
						.permitAll() 
						.anyRequest().authenticated())
//				.formLogin(Customizer.withDefaults()) //enable form
				.httpBasic(Customizer.withDefaults()) //in post man
				
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	
	
	
//	default one
//	@Bean
//	public UserDetailsService userDetails() {
//		UserDetails user1 = User
//				.withDefaultPasswordEncoder()
//				.username("ashok")
//				.password("grape")
//				.roles("user")
//				.build();
//		we can add more users
//		return new InMemoryUserDetailsManager(user1);
//	}
	

	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	
	
	@Bean
	public AuthenticationProvider providerr() {
		 DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		 provider.setPasswordEncoder(new BCryptPasswordEncoder(12));	
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	 

}
