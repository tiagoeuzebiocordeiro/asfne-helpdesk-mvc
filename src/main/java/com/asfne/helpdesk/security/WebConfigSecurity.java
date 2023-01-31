package com.asfne.helpdesk.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf()
		.disable() // Desativa as configurações padrão de memória do Spring
		.authorizeRequests() // Permirtir acessos
		.antMatchers(HttpMethod.GET, "/").permitAll() // Qualquer usuário acesso a página inicial por enquanto
		.anyRequest().authenticated()
		.and().formLogin().permitAll() // Permite qualquer usuario
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")); // <-- Mapeia url de login e trabalha com validações de usuário
	
	}
	
	@Override // Esse aqui vai autenticar o usuario em memoria
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
		.withUser("administrator").password("admin867asfne867").roles("ADMIN");
	}

	@Override // Ignora url especificas
	public void configure(WebSecurity web) throws Exception {

		web.ignoring().antMatchers("/css/**");
	
	}
	
}
