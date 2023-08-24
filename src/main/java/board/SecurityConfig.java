package board;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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
        auth.authenticationProvider(customAuthenticationProvider);
    }
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	// usernameNotFoundException 활성화
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setHideUserNotFoundExceptions(false);
        return authenticationProvider;
    }
//	
//	@Bean
//    public AuthenticationFailureHandler authenticationFailureHandler() {
//        return new LoginFailHandler();
//    }
	@Autowired
	private LoginFailHandler loginFailHandler;
	
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
    	
        http
        		.csrf().disable()
                .authorizeRequests()
                        .antMatchers("/auth/**", "/errorPage").permitAll()
                        .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/auth/login")
                    .loginProcessingUrl("/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .failureHandler(loginFailHandler)		//실패 처리
                    .permitAll()
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .permitAll()
              /*  .and()*/
/*                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)	//인증 예외
                .accessDeniedHandler(accessDeniedHandler*/
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