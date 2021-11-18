package ru.ravel.checkAdminPcHost.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
        http.antMatcher("/**").authorizeRequests()
                .antMatchers("/", "/login**", "/js/**", "/css/**", "/error**").permitAll()
                .anyRequest().permitAll()
                .and().logout().logoutSuccessUrl("/").permitAll()
                .and().formLogin().loginPage("/")
                .and().csrf().disable();
    }
}
