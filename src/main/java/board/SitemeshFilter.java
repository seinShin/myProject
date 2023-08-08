package board;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class SitemeshFilter extends ConfigurableSiteMeshFilter {
	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		builder.addDecoratorPath("/*", "/WEB-INF/decorator/default.jsp")
	    .addExcludedPath("/frontLayer/*")
//		.addExcludedPath("/index")
//		.addExcludedPath("/index")
;	
	}
}