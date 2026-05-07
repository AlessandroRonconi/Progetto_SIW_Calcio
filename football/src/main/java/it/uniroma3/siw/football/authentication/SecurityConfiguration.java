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

import static it.uniroma3.siw.football.model.Credentials.ADMIN_ROLE;

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
            // funzionalità base
            authorize.requestMatchers(HttpMethod.GET, "/", "/index", "/register", "/css/**", "/images/**",
                    "/favicon.ico").permitAll();
            authorize.requestMatchers(HttpMethod.POST, "/register", "/login").permitAll();
            
            // funzionalità pubbliche
            authorize.requestMatchers(HttpMethod.GET, "/tournaments", "/tournaments/*",
                    "/tournaments/*/participants", "/teams/*", "/tournaments/*/calendar").permitAll();
            
            // funzionalità da utente registrato
            authorize.requestMatchers(HttpMethod.GET,
                    "/games/*/comments", "/comments/*/edit").authenticated();
            authorize.requestMatchers(HttpMethod.POST,
                    "/games/*/comments", "/comments/*/edit").authenticated();

            // funzionalità da amministratore
            authorize.requestMatchers(HttpMethod.GET, "/admin/**").hasAnyAuthority(ADMIN_ROLE);
            authorize.requestMatchers(HttpMethod.POST, "/admin/**").hasAnyAuthority(ADMIN_ROLE);

            // altre richieste richiedono autenticazione
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
