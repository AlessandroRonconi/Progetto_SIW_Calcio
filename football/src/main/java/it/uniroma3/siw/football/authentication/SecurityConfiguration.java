package it.uniroma3.siw.football.authentication;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final DataSource dataSource;

    public SecurityConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery("SELECT username, password, 1 as enabled FROM credentials WHERE username=?");
        manager.setAuthoritiesByUsernameQuery("SELECT username, role FROM credentials WHERE username=?");
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain configure(final HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(authorize -> {
            authorize.requestMatchers(HttpMethod.GET, "/", "/index", "/register", "/login", "/css/**").permitAll();
            authorize.requestMatchers(HttpMethod.POST, "/register", "/login").permitAll();

            authorize.requestMatchers(HttpMethod.GET, "/tournaments", "/tournaments/{id}", "/tournaments/{id}/participants", "/tournaments/{id}/calendar", "/tournaments/{id}/classification").permitAll();
            authorize.requestMatchers(HttpMethod.GET, "/teams", "/teams/{id}").permitAll();

            authorize.requestMatchers(HttpMethod.GET, "/games/{id}/comments").authenticated();
            authorize.requestMatchers(HttpMethod.POST, "/games/{id}/comments").authenticated();
            authorize.requestMatchers(HttpMethod.GET, "/comments/{id}/edit").authenticated();
            authorize.requestMatchers(HttpMethod.POST, "/comments/{id}/edit").authenticated();

            authorize.requestMatchers(HttpMethod.GET, "/admin/**").hasAnyAuthority("ADMIN");
            authorize.requestMatchers(HttpMethod.POST, "/admin/**").hasAnyAuthority("ADMIN");
            authorize.anyRequest().authenticated();
        });

        httpSecurity.formLogin(form -> {
            form.loginPage("/login").permitAll();
            form.defaultSuccessUrl("/success", true);
            form.failureUrl("/login?error=true");
        });

        httpSecurity.logout(logout -> {
            logout.logoutUrl("/logout");
            logout.logoutSuccessUrl("/");
            logout.invalidateHttpSession(true);
            logout.deleteCookies("JSESSIONID");
            logout.clearAuthentication(true);
            logout.permitAll();
        });

        return httpSecurity.build();
    }

}