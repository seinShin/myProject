package board.front;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import board.security.CustomUserDetails;
import board.security.User;
import board.security.mapper.userMapper;


@Controller
public class indexController {
	
	private static final Logger logger = LoggerFactory.getLogger(indexController.class);
	
	@Autowired
	private userMapper userMapper;

	/**************************************************
	* @MethodName : index
	* @Description: 인덱스 페이지
	* @param request
	* @param model
	* @return String
	* @throws Exception 
	* @Author : se-in-Shin
	**************************************************/
	@GetMapping({"/", "/index"})
    public String index(HttpServletRequest request, Model model) throws Exception {
		logger.info("index controller---start");
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null) {
			Object principal = authentication.getPrincipal();
			if(principal instanceof CustomUserDetails) {
				CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
				if(userDetails != null){
					model.addAttribute("memberInfo", userMapper.getUserInfo(userDetails.getUsername()));
				}
			}
		}
		
		logger.info("index controller---end");
		
		return "index";
	}
	
	
	/**************************************************
	* @MethodName : pageMenuView
	* @Description: 공통 뷰 페이지 
	**************************************************/
	@GetMapping({"/{menu}/{page}"})
    public String pageMenuView(@PathVariable String menu, @PathVariable String page, HttpServletRequest request, Model model) throws Exception {
		logger.info("pageMenuView controller---start");
		
	
		logger.info("pageMenuView controller---end");
		return "/"+menu+"/"+page;
	}
	
	
	/**************************************************
	* @MethodName : pageView
	* @Description: 공통 뷰 페이지 
	**************************************************/
	@GetMapping({"/{page}"})
    public String PageView(@PathVariable String page, HttpServletRequest request, Model model) throws Exception {
		logger.info("PageView controller---start");
		
		
		
		logger.info("PageView controller---end");
		return page;
	}
	
	/**************************************************
	* @MethodName : login
	* @Description: 로그인 페이지
	**************************************************/
	@GetMapping({"/auth/login"})
    public String login(HttpServletRequest request, Model model, HttpSession session) throws Exception {
		logger.info("login controller---start");
		String error = (String) session.getAttribute("errorMessage");
		if (error != null) {
	        model.addAttribute("errorMessage", error);
	        session.removeAttribute("errorMessage"); // 세션 정보 삭제
	    }
		logger.info("login controller---end");
		return "/auth/login";
	}
	
	
}