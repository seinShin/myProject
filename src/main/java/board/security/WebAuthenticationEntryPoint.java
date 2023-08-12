package board.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


@Component
public class WebAuthenticationEntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
			System.out.println("WebAuthenticationEntryPoint");
			// 만약 ajax 요청이라면 접근 거부
			String ajaxHeader = request.getHeader("X-Requested-With");
			
			if(ajaxHeader != null && ajaxHeader.equals("XMLHttpRequest")) {
				System.out.println("ajax 요청");
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
			}else {
			// 웹페이지 요청이지만 권한이 없는 경우 로그아웃 처리
				System.out.println("권한이 없음");
				if(request.getHeader("Referer") != null) {
					request.setAttribute("msg", "로그아웃 되었습니다.");
            		request.setAttribute("page", "/auth/login");
				}
				request.getRequestDispatcher("/auth/login").forward(request, response);
			}
			
	}
}
