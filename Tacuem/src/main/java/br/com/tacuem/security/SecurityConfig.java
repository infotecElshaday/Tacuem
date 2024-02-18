package br.com.tacuem.security;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	TacuemAppHandler takaneAppHandler;
	
	@Autowired
	UserDetailsServicos userDetailsServicos;
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(request -> request
				.requestMatchers("/admin").hasAuthority("ADMIN")
				.requestMatchers("/user").hasAuthority("USUARIO")
				.requestMatchers("/salvar", "/css/**", "/img/**", "/js/**").permitAll()
				.anyRequest().authenticated())
		
		.formLogin(form -> form
				   .loginPage("/login")
				   .loginProcessingUrl("/login")
				   .successHandler(takaneAppHandler).permitAll())
		
		.logout(form -> form
				    .invalidateHttpSession(true)
				    .clearAuthentication(true)
				    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				    .logoutSuccessUrl("/login?logout").permitAll());
		
		return httpSecurity.build();
		
	}
	
	@Autowired
	public void configure (AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServicos).passwordEncoder(passwordEncoder());
	}

}
