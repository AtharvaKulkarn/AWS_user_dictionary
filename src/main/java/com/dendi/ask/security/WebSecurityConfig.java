package com.dendi.ask.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.inMemoryAuthentication()
				.withUser("admin").password(new BCryptPasswordEncoder().encode("password")).roles("ADMIN")
				.and()
				.withUser("dendi").password(new BCryptPasswordEncoder().encode("navidendi")).roles("USER")
				.and()
				.withUser("bijali").password(new BCryptPasswordEncoder().encode("ghantabijali")).roles("USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/getAllUsers").authenticated()
				.antMatchers("/addUser").hasAnyRole("ADMIN", "USER")
				.antMatchers("/updateUser").hasRole("ADMIN")
				.antMatchers("/deleteUser").hasRole("ADMIN")
				.and()
				.httpBasic()
				.and()
				.csrf().disable();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
