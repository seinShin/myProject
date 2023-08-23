package board;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import board.security.CustomAuthenticationProvider;
import board.security.LoginFailHandler;
import board.security.WebAccessDeniedHandler;
import board.security.WebAuthenticationEntryPoint;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;
	
	
	// 사용자 정보를 가져오고 인증
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider)
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
    }
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//	
//	@Bean
//    public AuthenticationFailureHandler authenticationFailureHandler() {
//        return new LoginFailHandler();
//    }
	@Autowired
	public LoginFailHandler loginFailHandler;
	
	@Autowired
	private WebAccessDeniedHandler accessDeniedHandler;

	@Autowired
    private WebAuthenticationEntryPoint authenticationEntryPoint;
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
        http.csrf().disable()
        
                .authorizeRequests(requests -> requests
                        .antMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .failureHandler(loginFailHandler)
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .permitAll())
				/*
				 * .exceptionHandling(handling -> handling .accessDeniedHandler
				 * (accessDeniedHandler) .authenticationEntryPoint(authenticationEntryPoint))
				 */
               
                ;
    }
    
    // css 파일들은 보이게
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/frontLayer/**");
    }
    
}