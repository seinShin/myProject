<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html
  lang="en"
  class="light-style layout-menu-fixed"
  dir="ltr"
  data-theme="theme-default"
  data-assets-path="/frontLayer/assets/"
  data-template="vertical-menu-template-free"
>
	<%@ include file="/WEB-INF/include/head.jsp" %>
<body>
		<!-- Layout wrapper -->
	    <div class="layout-wrapper layout-content-navbar">
	      <div class="layout-container">
				<!-- header content -->
				<%@ include file="/WEB-INF/include/aside.jsp" %>
				<!-- /header content -->
				<div class="layout-page">
				
					<%@ include file="/WEB-INF/include/nav.jsp" %>
					
					<div class="content-wrapper">
						<!-- page content -->
							<sitemesh:write property='body' />
						<!-- /page content -->
							
						<!-- footer content -->
							<%@ include file="/WEB-INF/include/footer.jsp" %>
						<!-- /footer content -->
						  <div class="content-backdrop fade"></div>
					</div>	
				</div>      
	      </div>
	      
	      <!-- Overlay -->
	      <div class="layout-overlay layout-menu-toggle"></div>
	    </div>
	    <!-- /Layout wrapper -->
<!-- 	    
	    
		<div class="buy-now">
		      <a
		        href="https://themeselection.com/products/sneat-bootstrap-html-admin-template/"
		        target="_blank"
		        class="btn btn-danger btn-buy-now"
		        >Upgrade to Pro</a
		      >
	    </div> -->
	
	    <!-- Core JS -->
	    <!-- build:js assets/vendor/js/core.js -->
	    <script src="/frontLayer/assets/vendor/libs/jquery/jquery.js"></script>
	    <script src="/frontLayer/assets/vendor/libs/popper/popper.js"></script>
	    <script src="/frontLayer/assets/vendor/js/bootstrap.js"></script>
	    <script src="/frontLayer/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>
	
	    <script src="/frontLayer/assets/vendor/js/menu.js"></script>
	    <!-- endbuild -->
	
	    <!-- Vendors JS -->
	    <script src="/frontLayer/assets/vendor/libs/apex-charts/apexcharts.js"></script>
	
	    <!-- Main JS -->
	    <script src="/frontLayer/assets/js/main.js"></script>
	
	    <!-- Page JS -->
	    <script src="/frontLayer/assets/js/dashboards-analytics.js"></script>
	
	    <!-- Place this tag in your head or just before your close body tag. -->
	    <script async defer src="https://buttons.github.io/buttons.js"></script>
			
	    <!-- End of Page Wrapper -->
</body>
</html>		
	