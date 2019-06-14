package hu.elte.szgy.istvan.rest;

import hu.elte.szgy.istvan.data.Felhasznalo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class TudorWebSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/","/extjs/**").permitAll()
                .antMatchers(HttpMethod.POST,"/temakorok/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/temakorok/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/temakorok/*").permitAll()

                .antMatchers(HttpMethod.DELETE,"/felhasznalok/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/felhasznalok/*").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET,"/tudakozasok/*").hasAnyRole("ADMIN", "TUDOR", "UGYFEL")
                .antMatchers(HttpMethod.POST,"/tudakozasok/*").hasRole("UGYFEL")
                .antMatchers(HttpMethod.PUT,"/tudakozasok/*/ertekeles").hasRole("UGYFEL")
                .antMatchers(HttpMethod.PUT,"/tudakozasok/*/valasz").hasRole("TUDOR")
                .antMatchers(HttpMethod.DELETE,"/tudakozasok/*/valasz").hasAnyRole("ADMIN", "TUDOR", "UGYFEL")

                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/login")
                .successForwardUrl( "/felhasznalok/dispatch" )
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {

//  SIMPLE USERSERVICE TO BE USED FOR TESTING ONLY
//     UserDetails user =
//             User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("password")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user);

        return new TudorUserService();
    }
}
