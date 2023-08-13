package board.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import board.security.CustomUserDetails;
import board.security.User;
import board.security.UserDetailService;


@RequestMapping("/rest/user")
@RestController
public class restMemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(restMemberController.class);
	
	@Autowired
    private UserDetailService userDetailService;
	
    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public String login(@RequestBody User user) {
        logger.info("login Controller --- start");

        // 실제 로그인 처리 로직을 구현하고 유저 정보를 검증하는 작업을 수행
        // userDetailService를 활용하여 유저 정보를 가져오고 인증을 수행
        
        // 사용자 정보로 UserDetails 객체 가져오기
        CustomUserDetails userDetails = (CustomUserDetails) userDetailService.loadUserByUsername(user.getEmail());

        try {
            // 인증 시도
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), userDetails.getAuthorities())
            );

            // 인증 성공 시 SecurityContext에 인증 정보 설정
            SecurityContextHolder.getContext().setAuthentication(authentication);

            logger.info("login Controller --- end");
            return "Login successful";
        } catch (AuthenticationException e) {
            logger.error("Authentication error: {}", e.getMessage());
            return "Login failed";
        }
        
    }
}
