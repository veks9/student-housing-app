package progi.projekt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import progi.projekt.security.jwt.JwtRequestFilter;

@EnableWebSecurity //ukljucivanje provjere razine pristupa
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private StudentUserDetailsService studentUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(studentUserDetailsService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    public void configure(org.springframework.security.config.annotation.web.builders.HttpSecurity http) throws Exception {
        http.userDetailsService(studentUserDetailsService);

        http.httpBasic();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers("/admin").hasAuthority("ROLE_ADMIN")
                .antMatchers("/user").hasAuthority("ROLE_STUDENT")
                .antMatchers("/").permitAll()
                .and().formLogin();

        http.headers().frameOptions().sameOrigin(); //za h2, nama ne treba?
        http.csrf().disable();

		/* trebati ce za jwt sessione
		httpSecurity.csrf().disable()
				.authorizeRequests().antMatchers("/authenticate").permitAll()
									.anyRequest().authenticated()
									.and()
									.exceptionHandling());

		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		 */


        super.configure(http);
    }
}

//dodati @Secured("ROLE_ADMIN") na metode koje poziva admin, @Secured("ROLE_STUDENT") na metode koje poziva student
