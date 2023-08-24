package board.security;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginFailHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		System.out.println(exception);
		
		String errorMessage;
		if(exception instanceof UsernameNotFoundException) {
			errorMessage = "아이디가 맞지 않습니다. 다시 확인해 주세요.";
		}else if(exception instanceof BadCredentialsException) {
			errorMessage = "비밀번호가 맞지 않습니다. 다시 확인해 주세요.";
		}else if(exception instanceof InternalAuthenticationServiceException) {
			errorMessage = "내부 시스템 문제로 로그인 요청을 처리할 수 없습니다. 관리자에게 문의하세요.";
		}else { 
			errorMessage = "알 수 없는 오류입니다. 관리자에게 문의하세요.";
		}
		
		String retUrl = "/auth/login";
		
		request.getSession().setAttribute("errorMessage", errorMessage);
		request.getRequestDispatcher(retUrl).forward(request, response);
	}

}
