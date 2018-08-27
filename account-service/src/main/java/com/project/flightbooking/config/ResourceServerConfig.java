package src.main.java.com.project.flightbooking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;


@Configuration
@EnableWebSecurity
@EnableResourceServer
@EnableAuthorizationServer
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    protected final PasswordEncoder passwordEncoder;

    public ResourceServerConfiguration() {
        passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * This is used to encode password.
     *
     * @return the {@link PasswordEncoder}
     */
    @Bean("bCryptPasswordEncoder")
    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").hasAnyRole("ADMIN")
                .anyRequest().authenticated();
    }

}