package pl.kraleppa.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.kraleppa.filter.JwtRequestFilter;
import pl.kraleppa.service.MyUserDetailsService;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    private final MyUserDetailsService myUserDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/users").authenticated()
                .antMatchers(HttpMethod.PATCH, "/api/v1/users").authenticated()
                .antMatchers(HttpMethod.GET, "/api/v1/baskets").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/v1/baskets").authenticated()
                .antMatchers(HttpMethod.GET, "/api/v1/orders").authenticated()
                .antMatchers(HttpMethod.POST, "/api/v1/orders").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/v1/games/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/v1/games/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/v1/games").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/orders/all").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/api/v1/orders/all").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/orders/credentials").hasRole("ADMIN")
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }



    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
