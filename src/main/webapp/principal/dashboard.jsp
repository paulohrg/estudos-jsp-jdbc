<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
  <jsp:include page="head.jsp"></jsp:include>
  </head>
  <body>
  <!-- Pre-loader start -->
  <jsp:include page="pre-load-start.jsp"></jsp:include>
  <!-- Pre-loader end -->
  <div id="pcoded" class="pcoded">
	  <div class="pcoded-overlay-box"></div>
      <div class="pcoded-container navbar-wrapper">
		<jsp:include page="menu-superior.jsp"></jsp:include>
          <div class="pcoded-main-container">
            <div class="pcoded-wrapper">
              <jsp:include page="menu-lateral.jsp"></jsp:include>
                <div class="pcoded-content">
                  <!-- Page-header start -->
                  <jsp:include page="submenu.jsp"></jsp:include>
                  <!-- Page-header end -->
                  <div class="pcoded-inner-content">
                  <!-- Main-body start -->
                    <div class="main-body">
                      <div class="page-wrapper">
                        <!-- Page-body start -->
                        <div class="page-body">
                          <div class="row">
							<a href="<%=request.getContextPath()%>/principal/estudos/tela-principal-estudo.jsp">EstudoJqueryAjax</a>
						  </div>
                        </div>
                      <!-- Page-body end -->
					  </div>
					  <div id="styleSelector"> </div>
 				   	</div>
				  </div>
                </div>
           </div>
         </div>
     </div>
  </div>
  <!-- Required Jquery -->
  <jsp:include page="jscript-file.jsp"></jsp:include>
</body>
</html>