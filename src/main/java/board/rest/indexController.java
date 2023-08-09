package board.rest;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import board.common.DataMap;
import board.common.util.HttpUtil;
import board.common.util.StringUtil;


@Controller
public class indexController {
	
	private static final Logger logger = LoggerFactory.getLogger(indexController.class);
	

	/**************************************************
	* @MethodName : index
	* @Description: 인덱스 페이지
	* @param request
	* @param model
	* @return String
	* @throws Exception 
	* @Author : se-in-Shin
	* @Version : 2021. 7. 13.
	**************************************************/
	@GetMapping({"/", "/index"})
    public String login(HttpServletRequest request, Model model) throws Exception {
		logger.info("index controller---start");
		
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
		logger.info(page);
		logger.info(menu);
		return "/"+menu+"/"+page;
	}
	
	/**************************************************
	* @MethodName : pageView
	* @Description: 공통 뷰 페이지 
	**************************************************/
	@GetMapping({"/{page}"})
    public String PageView(@PathVariable String page, HttpServletRequest request, Model model) throws Exception {
		logger.info("PageView controller---start");
		logger.info(page);

		return page;
	}
	
}