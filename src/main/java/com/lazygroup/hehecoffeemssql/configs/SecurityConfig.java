package com.lazygroup.hehecoffeemssql.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	StaffUserDetailsService staffUserDetailsService;

	@Autowired
	SecurityConfig(StaffUserDetailsService staffUserDetailsService) {
		this.staffUserDetailsService = staffUserDetailsService;
	}

	public void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(staffUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

	@Bean
	public SecurityFilterChain staffSecurityFilterChain(HttpSecurity http) throws Exception {

		http.userDetailsService(staffUserDetailsService).authorizeHttpRequests((request) -> request
				.requestMatchers("/admin-assets/**", "/assets/**", "/admin-login").permitAll()
				.anyRequest().authenticated())
				.formLogin(form -> form
						.loginPage("/admin-login")
						.loginProcessingUrl("/admin-login")
						.failureUrl("/admin-login?error")
						.defaultSuccessUrl("/admin/index")
						.permitAll())
				.logout(logout -> logout
						.logoutUrl("/admin/logout")
						.logoutSuccessUrl("/admin-login?logout")
						.deleteCookies("JSESSIONID"))
				.csrf(csrf -> csrf.disable());

		return http.build();
	}
}
