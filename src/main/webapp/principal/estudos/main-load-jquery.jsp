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
	<h1>tela principal load jquery</h1>
	<button onclick="carregarTabela();">carregar</button>
	<p id="carregar"> carregar aqui<p>
</body>
<script type="text/javascript">
	function carregarTabela() {
		$("#carregar").load("tabela-load-jquery.jsp");
	}
</script>
</html>