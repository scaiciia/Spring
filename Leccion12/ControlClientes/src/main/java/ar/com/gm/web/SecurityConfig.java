package ar.com.gm.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login").permitAll() //permiso al publico
                .requestMatchers("/editar/**", "/agregar/**" ,"/eliminar").hasRole("ADMIN") //permiso solo al admin
                .anyRequest().authenticated() //permiso solo a usuarios autenticados
            )
            .formLogin(login -> login
                .loginPage("/login") //pagina de login
                .permitAll()
            )
            .exceptionHandling(exceptionHandling -> exceptionHandling
                .accessDeniedPage("/errors/403")); //Acceso denegado
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{noop}123")
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password("{noop}123")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

}
