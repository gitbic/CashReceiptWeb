package ru.clevertec.cashReceiptWeb.security.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(bCryptPasswordEncoder());
    }

//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().antMatchers("/**");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.csrf().disable();

        http
                .csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and().httpBasic()
                .and().sessionManagement().disable();

//        http.authorizeRequests().antMatchers(
//                "/account/login",
//                "/account/logout",
//                "/account/registration",
//                "/account/add",
//                "/products",
//                "/account/logoutSuccessful").permitAll();
//        http.authorizeRequests().antMatchers("/admin").hasRole("ADMIN");
//        http.authorizeRequests().antMatchers("/**").authenticated();

        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/account/403");

        http.authorizeRequests().and().formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/account/login")
                .defaultSuccessUrl("/account/info")
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout().logoutUrl("/account/logout").logoutSuccessUrl("/account/logoutSuccessful");

    }
}