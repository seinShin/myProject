package board.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private UserDetailService userDetailService;
	
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		
		UserDetails userDetails = userDetailService.loadUserByUsername(username);
		
		//비밀번호가 일치하지 않으면 예외를 던짐
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("BadCredentialsException");
        } 
		
		//사용자의 인증정보를 담고있는 객체
		//principal - 사용자의 아이디나 식별자
		//credentials - 비밀번호 또는 인증을 위한 토큰
		// 이 객체로 인증 수행
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities());
		
		return authenticationToken;
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
	
	
}
