package com.alkemy.max.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@EnableWebSecurity
@Configuration
class WebAuthorization extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/register", "/auth/login").permitAll()
                .antMatchers("/api/").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.POST,"/api/characters", "/api/movies").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PATCH,"/api/characters", "/api/movies").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/characters", "/api/movies").hasAuthority("ADMIN");
        http.formLogin()

                .usernameParameter("email")

                .passwordParameter("password")

                .loginPage("/auth/login");

        http.logout().logoutUrl("/auth/logout");


        http.csrf().disable();


        http.headers().frameOptions().disable();


        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));


        http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

        http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));


        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());

    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {

            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        }
    }

}