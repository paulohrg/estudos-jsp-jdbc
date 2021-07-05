<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<h3>Capturando excessões com jquery</h3>
	<input type="text" value="Valor informado" id="valorInformado">
	<input type="button" onclick="valorInform();" value="testar">
</body>
<script type="text/javascript">
	function valorInform() {
		valorInformado = $("#valorInformado").val();
		
		
		$.ajax({
			method: "POST",
			url: "capturarExcecao",
			data: {valorParam : valorInformado}
		})
		.done(function(response){
			alert("Sucesso: " + response);
		})
		.fail(function(xhr, status, errorThrown){
			alert("Error: " + xhr.responseText);
		});;
	}
</script>
</html>