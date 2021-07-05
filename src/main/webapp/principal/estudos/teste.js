	var target;
	var arquivo;
	
	function atualizaArquivo() {

		arquivo = document.querySelector("input[type=file]").files[0];
		var reader = new FileReader();
		alert(arquivo);
		reader.onloadend = function () {
			target = reader.result;
		};
		if(arquivo){
			/*salva o upload*/
			reader.readAsDataURL(arquivo);
		}
	}
	
	function salvaUpload() {		
		if(arquivo){
			/*salva o upload*/
			$.ajax({
				method: "POST",
				url: "UploadFile",
				data: {
					fileUpload : target,
					fileName : arquivo.prototype.name
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
	}