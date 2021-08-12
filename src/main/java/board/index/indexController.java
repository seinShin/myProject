/**
 * 
 */
package board.index;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import board.common.util.HttpUtil;

/**************************************************
* @FileName : indexController.java
* @Description:
* @Author : se-in shin
* @Version : 2021. 8. 12.
* @Copyright : ⓒADUP. All Right Reserved
**************************************************/
@Controller
public class indexController {
	
	
	
	@RequestMapping(value= {"/","/index"}, method= {RequestMethod.GET, RequestMethod.POST})
	public String index(HttpServletRequest request, Model model) throws Exception {

		return "index";
	}
	
	@RequestMapping(value= {"/","/tables"}, method= {RequestMethod.GET, RequestMethod.POST})
	public String tables(HttpServletRequest request, Model model) throws Exception {

		return "tables";
	}

}
