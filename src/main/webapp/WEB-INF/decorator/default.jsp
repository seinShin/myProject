<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang ="ko">
		<%@ include file="/WEB-INF/include/head.jsp" %>
<body>

		<!-- header content -->
			<%@ include file="/WEB-INF/include/header.jsp" %>
		<!-- /header content -->

		<!-- page content -->
		<sitemesh:write property='body' />
		<!-- /page content -->
		
		<!-- footer content -->
			<%@ include file="/WEB-INF/include/footer.jsp" %>
		<!-- /footer content -->
</body>
</html>		
	