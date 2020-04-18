package co.edu.icesi.fi.tics.tssc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoggingAccessDeniedHandler accessDeniedHandler;

//	@Autowired
//	private MyCustomUserDetailsService userDetailsService;

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(authenticationProvider());
//	}

//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		authProvider.setUserDetailsService(userDetailsService);
//		authProvider.setPasswordEncoder(encoder());
//		return authProvider;
//	}
//
//	@Bean
//	public PasswordEncoder encoder() {
//		return new BCryptPasswordEncoder(11);
//	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		
		httpSecurity.formLogin().loginPage("/login").permitAll().and().authorizeRequests()
		.antMatchers("/index").permitAll()
		.antMatchers("/topics/**").hasAnyRole("superadmin")
		.antMatchers("/game/**").hasAnyRole("superadmin","admin")
		.antMatchers("/story/**").hasAnyRole("superadmin","admin")
		.anyRequest().authenticated().and().httpBasic().and().logout()
		.invalidateHttpSession(true).clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
		.permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
		
//		httpSecurity.formLogin().loginPage("/login").permitAll().and().authorizeRequests()
//		.antMatchers("/index").permitAll()
//		.antMatchers("/users/**").hasAnyRole("admin")
//		.antMatchers("/apps/**").hasAnyRole("doctor")
//		.antMatchers("/apps/**").hasAnyRole("patient")
//		.anyRequest().authenticated().and().httpBasic().and().logout()
//		.invalidateHttpSession(true).clearAuthentication(true)
//		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
//		.permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);

	}
	
}