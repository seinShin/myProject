package board.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;


@Component
public class LoginFailHandler implements AuthenticationFailureHandler{
		
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		
		  String loginId = request.getParameter("id");
		  String loginPw = request.getParameter("pw");
		  
		  request.setAttribute("msg", "로그인 정보가 일치하지 않습니다. /n 아이디 및 비밀번호를 확인해주세요.");
		  request.setAttribute("loginId", loginId);
		  request.setAttribute("loginPw", loginPw);
		  
		  String failureUrl = "/auth/login";
			/* response.sendRedirect(failureUrl); */
		  request.getRequestDispatcher(failureUrl).forward(request, response);
	}

}
