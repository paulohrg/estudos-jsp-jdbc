<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="//cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css">
<jsp:include page="../head.jsp"></jsp:include>
</head>
<body>
  <!-- Pre-loader start -->
  <jsp:include page="../pre-load-start.jsp"></jsp:include>
  <!-- Pre-loader end -->
  <div id="pcoded" class="pcoded">
	  <div class="pcoded-overlay-box"></div>
      <div class="pcoded-container navbar-wrapper">
		<jsp:include page="../menu-superior.jsp"></jsp:include>
          <div class="pcoded-main-container">
            <div class="pcoded-wrapper">
              <jsp:include page="../menu-lateral.jsp"></jsp:include>
                <div class="pcoded-content">
                  <!-- Page-header start -->
                  <jsp:include page="../submenu.jsp"></jsp:include>
                  <!-- Page-header end -->
                  <div class="pcoded-inner-content">
                  <!-- Main-body start -->
                    <div class="main-body">
                      <div class="page-wrapper">
                        <!-- Page-body start -->
                        <div class="page-body">
                          <div class="row" style="display: inline-block;">
							<h3 style="text-align:center" style="margin-bottom:10px;">Lista de Arquivos</h3>
							<table id="minhaTabela" class="display" style="width:100%" >
							        <thead>
							            <tr>
							                <th>id</th>
							                <th>Arquivo</th>
							                <th>Açoes</th>
							            </tr>
							        </thead>
							</table>
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
<jsp:include page="../jscript-file.jsp"></jsp:include>

</body>
 <script type="text/javascript">
 $(document).ready(function(){
	 var id;
	 var arquivo;
	 
     $('#minhaTabela').DataTable( {
	        "processing": true,
	        "serverSide": true,
	        "ajax": "carregarDadosDataTable", //retorno dos dados do servidor (json)
	        "columns": [
            {
                data: 'id',
                render: function(data){
                	this.id = data;
                	return data;
                }
            },
            {
                data: 'arquivo',
                render: function(data){
                	this.arquivo = data;
                	return data;
                }
                
            },
            {
               	render: function() {
               		return '<a href="UploadFile?acao=download&idArquivo='+ this.id +'&nomeArquivo=' + this.arquivo + '">Download</a>';
               		}
            }]
	    } );
 });
</script>
</html>