package com.proyecto.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.proyecto.demo.service.Impl.UsuarioServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	@Qualifier("usuarioService")
	private UsuarioServiceImpl userService;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
				/*.regexMatchers("/inicio/listAlumnos").hasAuthority("ROL_ADMIN")
				.regexMatchers("/inicio/inicioAdmin").hasAuthority("ROL_ADMIN")
				.regexMatchers("/inicio/listCiclos").hasAuthority("ROL_ADMIN")
				.regexMatchers("/inicio/listRRHH").hasAuthority("ROL_ADMIN")
				.regexMatchers("/inicio/listNoticias").hasAuthority("ROL_ADMIN")
				.regexMatchers("/inicio/miPerfilAlumno").hasAuthority("ROL_ALUMNO")
				.regexMatchers("/inicio/miPerfilRRHH").hasAuthority("ROL_RRHH")/*/
				.antMatchers("/", "/imgs/**", "/photos/**", "/auth/**", "/css/**", "/inicio", "/files/**")
				.permitAll()
			.anyRequest()
				.permitAll()
			.and()
		.formLogin()
			.loginPage("/auth/login")
			.defaultSuccessUrl("/inicio", true)
			.usernameParameter("email")
			.passwordParameter("password")
			.permitAll()
			.and()
		.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/auth/login?logout")
			.permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}

	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**");			
			}
		};
	}
}
