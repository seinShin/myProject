package board.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import board.common.CamelMap;
import board.common.DataMap;
import board.common.util.HttpUtil;
import board.common.util.Paging;
import board.rest.mapper.indexMapper;
import board.common.util.StringUtil;

/**************************************************
* @FileName : indexController.java
* @Description:
* @Author : se-in shin
* @Version : 2021. 8. 12.
* @Copyright : ⓒADUP. All Right Reserved
**************************************************/
@RequestMapping("/board/rest")
@RestController
public class restController {
	
	@Autowired
	private indexMapper indexMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(restController.class);
	
	/**************************************************
	* @MethodName : index
	* @Description: 게시판 메인 조회
	* @param request
	* @return ModelAndView
	* @throws Exception 
	* @Author : se-in shin
	* @Version : 2021. 8. 18.
	**************************************************/
	@SuppressWarnings("unchecked")
	@RequestMapping(value={"/indexList"}, method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView index(HttpServletRequest request) throws Exception {
		DataMap paramMap = HttpUtil.getRequestDataMap(request);
		ModelAndView mv = new ModelAndView("jsonView");
		
		int rowMax = 0;
		int pageCount = 10;
		int rowCount = 10;
		
		String nowPage = paramMap.getString("nowPage");
		
		if (StringUtils.isEmpty(nowPage)) {
			nowPage = "1";
		}
		
		paramMap.put("nowPage", nowPage);
		
		// rowMax
				try {
					rowMax = indexMapper.boardCount(paramMap);
				} catch (Exception e) {
					logger.error("게시물 갯수 조회 오류 : {}", e);
				}
				
		// 페이지 정보
		CamelMap pageMap = Paging.initDataMapPage(rowMax, pageCount, rowCount, Integer.parseInt(nowPage));

		paramMap.put("rowCount", rowCount);
		paramMap.put("scopeRow", pageMap.getInt("scopeRow"));
				
		
		List <CamelMap> resultList = null;
		
		try {
			resultList = indexMapper.indexList(paramMap);
		} catch (Exception e) {
			logger.debug("게시판 목록 조회 오류", e);
		}
		
		logger.info("index Controller"); 
		
		mv.addObject("resultList", resultList);
		mv.addObject("pageMap", pageMap);
		
		System.out.println(mv);
		return mv;
	}
	
	/**************************************************
	* @MethodName : indexInfo
	* @Description: 게시판 상세 조회
	* @param request
	* @return ModelAndView
	* @Author : se-in shin
	* @Version : 2021. 8. 18.
	**************************************************/
	@RequestMapping(value= {"/indexInfo"}, method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView indexInfo(HttpServletRequest request) {
		DataMap paramMap = HttpUtil.getRequestDataMap(request);
		ModelAndView mv = new ModelAndView("jsonView");
		
		CamelMap resultInfo = null;
		try {
			resultInfo = indexMapper.indexInfo(paramMap);
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.debug("게시판 상세 정보 조회 오류", e);
		}
		
		mv.addObject("resultInfo",resultInfo);
		return mv;
	}

	
	/**************************************************
	* @MethodName : indexWrite
	* @Description: 게시판 등록/수정
	* @param request
	* @param model
	* @return Object
	* @throws Exception 
	* @Author : se-in shin
	* @Version : 2021. 8. 18.
	**************************************************/
	@SuppressWarnings("unchecked")
	@RequestMapping(value= {"/indexWrite"}, method= {RequestMethod.GET, RequestMethod.POST})
	public Object indexWrite(HttpServletRequest request, Model model) throws Exception{
		logger.debug("indexWrite Controller");
		
		DataMap paramMap = HttpUtil.getRequestDataMap(request);
		DataMap rstMap = new DataMap();
		
		String mode = paramMap.getString("mode");
		String seq = paramMap.getString("seq");
		
		int rst = 0;
		boolean sw = false;
		
		if ("MOD".equals(mode) && !StringUtil.isEmpty(seq)) rst = indexMapper.indexUpdate(paramMap);
		else rst = indexMapper.indexWrite(paramMap);
	
		if (rst > 0) sw = true;
		
		rstMap.put("sw",sw);
		rstMap.put("seq", paramMap.getString("seq"));
		
		return rstMap;
	}
	
	
	@RequestMapping(value= {"/indexDelete"}, method = {RequestMethod.GET, RequestMethod.POST})
	public boolean indexDelete(HttpServletRequest request, Model model) {
		
		DataMap paramMap = HttpUtil.getRequestDataMap(request);
		
		int rst=0;
		boolean sw = false;
		
		try {
			rst = indexMapper.indexDelete(paramMap);
			if(rst > 0) {
				sw = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.debug("게시물 삭제 오류", e);
		}
		return sw;
		
	}
	
}
