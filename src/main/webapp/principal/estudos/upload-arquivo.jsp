<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>upload Arquivo</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<h3>Upload de Arquivo</h3>
	<table>
		<tr>
			<td><input type="file" id="arquivo" name="arquivo" onchange="atualizaArquivo();"></td>
		</tr>
		<tr>
			<td><button onclick="salvaUpload();">salvar</button></td>
		</tr>
	</table>
	<br/>
	<br/>
	<a href="UploadFile?acao=carregar">Buscar Arquivos</a>
	<table border=1>
		<tr>
			<th>id</th>
			<th>Arquivo</th>
			<th>Ações</th>
		</tr>
		<c:forEach items="${listaArquivosUsuario}" var="arquivo">
			<tr>
				<td>${arquivo.id}</td>
				<td>${arquivo.nomeArquivo}</td>
				<td><a href="UploadFile?acao=download&idArquivo=${arquivo.id}&nomeArquivo=${arquivo.nomeArquivo}">Download arquivo</a></td>
			</tr>
		</c:forEach>
	</table>
	
</body>
<script type="text/javascript">
	var target;
	var arquivo;
	
	function atualizaArquivo() {
	
		arquivo = document.querySelector("input[type=file]").files[0];
		var reader = new FileReader();
		reader.onloadend = function () {
			target = reader.result;
		};
		if(arquivo){
			/*salva o upload*/
			reader.readAsDataURL(arquivo);
		}
	};
	
	function salvaUpload() {		
		if(arquivo){
			/*salva o upload*/
			$.ajax({
				method: "POST",
				url: "UploadFile",
				data: {
					fileUpload : target,
					fileName : arquivo.name
					}
				})
				.done(function(response){
					alert("Sucesso: " + response);
				})
				.fail(function(xhr, status, errorThrown){
					alert("Error: " + xhr.responseText);
				});
		}else{
			alert("Adicione um arquivo para salvar!")
		}
	};
</script>
</html>