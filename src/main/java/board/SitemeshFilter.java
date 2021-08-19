package board;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

/**************************************************
* @FileName   : SitemeshFilter.java
* @Description: 사이트매시 필터
* @Author     : Seung-Jun. Kim
* @Version    : 2021. 07. 09.
* @Copyright  : ⓒADUP. All Right Reserved
**************************************************/
public class SitemeshFilter extends ConfigurableSiteMeshFilter {
	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		builder.addDecoratorPath("/*", "/WEB-INF/decorator/default.jsp")		
		;
	}
}