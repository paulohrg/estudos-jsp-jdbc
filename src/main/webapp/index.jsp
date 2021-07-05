<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/autenticacao.css">
<title>Insert title here</title>
</head>
<body>
	<div class="container login-container">
    	<div class="row">
        	<div class="col-md-6 login-form-1 container">
				<h3>Login</h3>
				<form action="ServletLogin" method="post" class="needs-validation" novalidate>
					<input type="hidden" value="<%= request.getParameter("url")%>" name="url">
					<div class="form-group">
						<label>Login:</label>
						<input type="text" class="form-control" name="login" required>
						<div class="invalid-feedback">
					      Obrigatório.
					    </div>
					</div>
					<div class="form-group">
						<label>Senha:</label>
						<input type="password" class="form-control" name="senha" required>
						<div class="invalid-feedback">
					      Obrigatório.
					    </div>
					</div>
					<div class="form-group">
						<a href="">Cadastrar-se</a>
					</div>
					<div class="form-group">
						<input type="submit" class="btnSubmit" value="Enviar">
					</div>
					<h6 class="alerta">${msg}</h6>
				</form>
				
			</div>
		</div>
	</div>
	
	
	<!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
	<script type="text/javascript">
		(function () {
			  'use strict'
	
			  // Fetch all the forms we want to apply custom Bootstrap validation styles to
			  var forms = document.querySelectorAll('.needs-validation')
	
			  // Loop over them and prevent submission
			  Array.prototype.slice.call(forms)
			    .forEach(function (form) {
			      form.addEventListener('submit', function (event) {
			        if (!form.checkValidity()) {
			          event.preventDefault()
			          event.stopPropagation()
			        }
	
			        form.classList.add('was-validated')
			      }, false)
			    })
			})()
		</script>
</body>
</html>