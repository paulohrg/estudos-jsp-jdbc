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
			<td><input type="file" id="arquivo" name="arquivo" onchange="mostraUpload()"></td>
		</tr>
		<tr>
			<td><img alt="Imagem" src="" id="targetImg" width="200px" height="200px" hidden="true"></td>
		</tr>
		<tr/>
		<tr>
			<td><button onclick="salvaUpload();">salvar</button></td>
		</tr>
	</table>
	<br/>
	<br/>
	<a href="UploadImage?acao=carregar">Carregar imagens</a>
	<table border=1>
		<tr>
			<th>ID</th>
			<th>Login</th>
			<th>Ações</th>
		</tr>
		<c:forEach items="${ListaUserImagens}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.login}</td>
				<td><a href="UploadImage?acao=download&idUser=${user.id}&tipoArquivo=${user.tipoArquivo}">Download Foto</a></td>
			</tr>
		</c:forEach>
	</table>
	
</body>
<script type="text/javascript">
	
	function mostraUpload() {
		var target = document.querySelector("img");
		var arquivo = document.querySelector("input[type=file]").files[0];
		var reader = new FileReader();
		
		reader.onloadend = function () {
			target.src = reader.result;
			target.hidden = false;
		};
		
		if(arquivo){
			reader.readAsDataURL(arquivo);
		}else{
			target.src="";
		}
	};
	
	function salvaUpload() {
		var target = document.querySelector("img");
		var arquivo = document.querySelector("input[type=file]").files[0];
		
		if(arquivo){
			/*salva o upload*/
			$.ajax({
				method: "POST",
				url: "UploadImage",
				data: {fileUpload : target.src}
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
	}
</script>
</html>