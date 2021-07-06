<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>Relatório de usuários</h3>
	<a href="ServiletDownloadRelatorio?tipoExport=pdf">Gerar PDF</a>
	<a href="ServiletDownloadRelatorio?tipoExport=xls">Gerar Excel</a>
	<a href="enviarEmail?tipoExport=pdf">Enviar por email</a>
</body>
</html>