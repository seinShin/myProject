///**
// * 
// */
//package board;
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import board.SitemeshFilter;
//
///**************************************************
//* @FileName : ServletFileterConfig.java
//* @Description: 사이트매시 설정
//* @Author : se-in shin
//* @Version : 2021. 8. 12.
//* @Copyright : ⓒADUP. All Right Reserved
//**************************************************/
//
//@Configuration
//public class ServletFileterConfig {
//		
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@Bean
//	public FilterRegistrationBean siteMeshFilter() {
//		FilterRegistrationBean filter = new FilterRegistrationBean();
//		filter.setFilter(new SitemeshFilter());
//		return filter;
//	}
//	
//	
//}
