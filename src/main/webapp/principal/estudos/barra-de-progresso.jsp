<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!-- <script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>  -->

<style type="text/css">
	 
	 #myProgress{
	 	width: 20%;
	 	min-width: 150px;
	 	background-color: #ddd;
	 }
	 
	 #myBar{
	 	width: 0%;
	 	height: 20px;
	 	background-color: #4CAF50;
	 }
	 
	 .ui-progessbar{
	 	position:relative;
	 	max-width: 200px;
	 	left: 50%;
	 }
	 .progress-label{
	 	position:relative;
	 	top:4%;
	 	text-align: center;
	 	font-weight: bold;
	 	text-shadow: 1px 1px #fff;
	 }
</style>
</head>
<body>
	<h1>barra de progresso</h1>
	<h3>exemplo com js</h3>
	<div id="myProgress">
		<div id="myBar">
		</div>
	</div>
	<br/>
	<button onclick="iniciarProgresso()">Iniciar progresso</button>
	<br/>
	<br/>
	<h3>exemplo com jquery</h3>
	<div id="progressbar" class="ui-progessbar">
		<div class="progress-label">
		carregando...
		</div>
	</div>
</body>
<script type="text/javascript">

	//Script barra de progresso por Jquery:
	$(function () {
		var progresso = $("#progressbar"), progresslabel = $(".progress-label");
		
		progresso.progressbar({//cria a barra no div
			value: false,
			change: function (){
				progresslabel.text (progresso.progressbar('value') + "%");				
			},
			complete: function () {
				progresslabel.text ("Completo!")
			}
		});
		
		function progress() {
			var val = progresso.progressbar("value") || 0;
			progresso.progressbar("value", val + 2);
			
			if(val<100){
				setTimeout(progress, 80);
			}
		}
		
		setTimeout(progress, 2000);
	});
	
	//Script barra de progresso por JS:
	function iniciarProgresso() {
		var progresso = document.getElementById("myBar"); 
		var wid = 1;
		var id = setInterval(frame, 50);
		 
		function frame() {
			if(wid>=100){
				clearInterval(id);
			}else{
				wid++;
				progresso.style.width = wid + "%";
			}
		}
	}
</script>
</html>