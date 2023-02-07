package com.asfne.helpdesk.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private ImplementacaoUserDetailsService implementacaoUserDetailsService;
		
	@Override // Configura as solicitações de acesso por http
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf()
			.disable() // Desabilita as configurações padrão de memória
			.authorizeRequests() // Permitir restringir acessos
			//.antMatchers(HttpMethod.GET, "/").permitAll() // Qualquer usuario acessa a pagina inicial sem precisar estar logado
			.antMatchers(HttpMethod.GET, 
					"/usuario/usuarios", "/usuario/cadastrarusuario", 
					"/usuario/usuariospage", "/usuario/pesquisarusuario",
					"/usuario/editarusuario/**", "/usuario/config-acessos/**",
					"/usuario/gravarrole/**", "/usuario/removerrole/**"
					
					).hasAnyRole("ADMIN")
					
			.antMatchers(HttpMethod.GET,		

					"/chamados/cadastrar", 
					"/chamados/editar/**",
					"/chamados/editar",
					"/chamados/salvar", 
					"/chamados/excluir/**",
					"/chamados/listar",
					
					"/setores/cadastrar",
					"/setores/editar/**",
					"/setores/editar",
					"/setores/listar",
					"/setores/salvar",
					"/setores/excluir/**",
					
					"/funcionarios/cadastrar", 
					"/funcionarios/editar/**",
					"/funcionarios/editar",
					"/funcionarios/salvar",
					"/funcionarios/excluir/**",
					"/funcionarios/listar"
	
					).hasAnyRole("ADMIN","FUNCIONARIO")
			
			
			
			.anyRequest()
				.permitAll()
				.and()
			.formLogin() // Qualquer usuário pode acessar o formulário de login
				.loginPage("/login") // Informa para o spring security que tem que  chamar a pagina login.html
				.defaultSuccessUrl("/home") // Se o login for realizado com sucesso redireciona para a pagina index.htm (obs: testar depois com "/index")
				//.failureUrl("/login?error=true") // Se o login falhar permanece na pagina de login.html	
				.failureUrl("/login-error")
				.and()
			.logout()
				.logoutSuccessUrl("/login"); // Mapeia a URL de logout e invalida o usuário autenticado 			
		
	}
	
	@Override // Cria autenticação do usuário com banco de dados ou em memória
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {	
		auth.userDetailsService(implementacaoUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
		/*
		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
			.withUser("Romulo")
			.password("123")
			.roles("123");
        */
	}
	
	@Override // Ignora URL especificas
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/webjars/**");
	}
	
	
}
