package board;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import board.common.DataMap;
import board.common.util.HttpUtil;
import board.common.util.StringUtil;


/**************************************************
* @FileName : IndexController.java
* @Description: 인덱스 페이지 컨트롤러
* @Author : Hyung-Seon. Yoon
* @Version : 2021. 7. 13.
* @Copyright : ⓒADUP. All Right Reserved
**************************************************/
@Controller
@RequestMapping(value={"/"})
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
	@RequestMapping(value={"/", "/index"}, method={RequestMethod.GET, RequestMethod.POST})
    public String index(HttpServletRequest request, Model model) throws Exception {
		logger.info("index controller");
		
		DataMap paramMap = HttpUtil.getRequestDataMap(request);
		
		HttpUtil.getParams(paramMap, model);
		
		logger.info("index controller");
		
		return "index";
	}
	
	
	/**************************************************
	* @MethodName : adminPageView
	* @Description: 관리자 컨텐츠 공통 뷰 페이지 
	* @param menu
	* @param page
	* @param request
	* @param model
	* @return String
	* @throws Exception
	* @Author     : Seung-Jun. Kim
	* @Version    : 2021. 2. 8
	**************************************************/
	@RequestMapping(value="/{page}", method={RequestMethod.GET, RequestMethod.POST})
    public String adminPageView(@PathVariable String page, HttpServletRequest request, Model model) throws Exception {
		
		DataMap paramMap = HttpUtil.getRequestDataMap(request);
		
		String mode = paramMap.getString("mode");
		String seq = paramMap.getString("seq");
		
		if(StringUtil.isEmpty(seq) || StringUtil.isNull(seq)) {
			mode = "INS";
		}else {
			mode = "MOD";	
		}
		
		model.addAttribute("mode", mode);
		
		HttpUtil.getParams(paramMap, model);
		
		model.addAttribute("nowDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

		return page;
	}
	
}