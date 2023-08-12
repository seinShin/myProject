package board.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import board.security.WebAccessDeniedHandler;


@Component
public class WebAccessDeniedHandler implements AccessDeniedHandler{
	private static final Logger logger = LoggerFactory.getLogger(WebAccessDeniedHandler.class);
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws ServletException, IOException {
		
		response.setStatus(HttpStatus.FORBIDDEN.value());
		// 현재 페이지 확인 - 관리자 페이지가 존재한다면 사용
		/*
		 * boolean adminPage = false; if(request.getRequestURI().indexOf("/admin")>=0) {
		 * adminPage = true; }
		 */
		System.out.println("webAccessDenied----------");
		// 실제로 접근이 거부되었을 때 (accessDeniedException 객체가 인스턴스인 경우)
		if(accessDeniedException instanceof AccessDeniedException){
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			// 사용자의 로그인 정보는 존재하지만 권한이 없을 때 -> 관리자를 생성하게된다면 사용
			if(auth != null) {
				if(((User)auth.getPrincipal()).getRole().equals("ROLE_ADMIN")) {
					//관리자 페이지를 추가한다면 URL 변경
					request.setAttribute("msg", "접근 권한이 없는 사용자입니다.");
					request.setAttribute("page", "/auth/login");
				}else if(((User)auth.getPrincipal()).getRole().equals("ROLE_USER")){
					request.setAttribute("msg", "접근 권한이 없는 사용자입니다.");
					request.setAttribute("page", "/auth/login");
				}
			}else {
			// 사용자가 인증되지 않은 경우
			// 사용자의 로그인 정보가 아예 존재하지 않을 때
				logger.info("null-----");
				request.setAttribute("msg", "로그인 정보가 존재하지 않습니다.");
				
				//관리자가 존재할 경우 페이지 분기 처리 필요
				request.setAttribute("page", "/auth/login");
				
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				SecurityContextHolder.clearContext();
			}
		}else {
			// accessDeniedException 객체가 인스턴스가 아닌 경우
			logger.info(accessDeniedException.getClass().getCanonicalName());
		}
		
		// 관리자 페이지가 존재할 경우 페이지 분기 처리
		/*
		 * request.getRequestDispatcher("/auth/access-denied").forward(request,
		 * response);
		 */
	}
	
}
