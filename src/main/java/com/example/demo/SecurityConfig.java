package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.service.ServiceUsuarioImpl;

@Configuration
@SuppressWarnings("deprecation")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ServiceUsuarioImpl serviceusuario;
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
        http.authorizeRequests()
        .antMatchers("/resources/**").permitAll()
        .antMatchers("/").permitAll()
        .antMatchers("/orec/**")
        .hasAnyRole("ADMIN")
        .anyRequest()
        .fullyAuthenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .usernameParameter("username")
        .passwordParameter("password")
        .loginProcessingUrl("/login.action")
        .defaultSuccessUrl("/")
        .failureUrl("/login?error")
        .permitAll()
        .and()
    .logout()
     .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
     .logoutSuccessUrl("/login?logout")
     .deleteCookies("my-remember-me-cookie")
        .permitAll();
	}
}
