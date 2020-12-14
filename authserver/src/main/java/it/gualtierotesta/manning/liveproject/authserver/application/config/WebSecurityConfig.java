package it.gualtierotesta.manning.liveproject.authserver.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Web security configuration
 *
 * @author gualtiero.testa
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    /**
//     * In memory UserDetailsService with a dummy user
//     *
//     * @return the UserDetailsService bean
//     */
//    @Bean
//    public UserDetailsService uds() {
//        var uds = new InMemoryUserDetailsManager();
//        var u = User.withUsername("john")
//            .password("12345")
//            .authorities("read")
//            .build();
//        uds.createUser(u);
//        return uds;
//    }

    /**
     * Define a NoOp password encoders as the default password encoder
     *
     * @return the PasswordEncoder bean
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http.csrf(c -> c.ignoringAntMatchers("/api/**"))
            .authorizeRequests()
            .mvcMatchers("/api/**").permitAll()
            .anyRequest().authenticated().and()
            .formLogin();
    }
}
