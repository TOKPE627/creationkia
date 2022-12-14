package com.javatechie.awselasticbeanstalkexample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.javatechie.awselasticbeanstalkexample.service.impl.UserSecurityService;
import com.javatechie.awselasticbeanstalkexample.utility.SecurityUtility;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private Environment env;

	@Autowired
	private UserSecurityService userSecurityService;

	private BCryptPasswordEncoder passwordEncoder() {
		return SecurityUtility.passwordEncoder();
	}

	private static final String[] PUBLIC_MATCHERS = {
			"/css/**",
			"/js/**",
			"/images/**",
			"/fonts/**",
			"/search",
			"/assets/**",
			"/dashboard","/category","/style","/brand","/univers",
			"/cart","/customer"
	};
	


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 // http.csrf().disable().cors().disable().authorizeRequests().antMatchers("/ads").permitAll();

		  http.cors().and().csrf().disable().
          authorizeRequests()
          .antMatchers(PUBLIC_MATCHERS).permitAll()
         // .anyRequest()
          //.authenticated()
          ;

		/*
		 * http .authorizeRequests(). //antMatchers("/**"). antMatchers(PUBLIC_MATCHERS)
		 * .permitAll().anyRequest().authenticated();
		 */

		http
			.csrf().disable().cors().disable()
			.formLogin().failureUrl("/login?error")
			.defaultSuccessUrl("/dashboard")
			.loginPage("/login").permitAll()
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/").deleteCookies("remember-me").permitAll()
			.and()
			.rememberMe()
			//.and().antMatcher("/adds")
			;
		//http.antMatcher("/adds");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
	}

}
