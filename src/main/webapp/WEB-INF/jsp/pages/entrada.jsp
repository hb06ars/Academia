<head>
	<meta charset="UTF-8">
	<title>Academia</title>
	<meta name="keywords" content="HTML5 Admin Template" />
	<meta name="description" content="Sistema de Academia">
	<meta name="author" content="Academia">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="shortcut icon" href="assets/images/logo.png" type="image/ico">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="/assets/vendor/font-awesome/css/font-awesome.css" />
</head>
<!-- TAGS -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!-- TAGS -->
<!-- INICIO BODY -->
<jsp:include page="includes/modais/modalPopup.jsp" />

<script>
window.onload = function () {
	if('${popup}' != null && '${popup}' != ''){
		modalPopup('${popup}')
	}
}
	
function redirecionar(link){
	window.location.href=link;
}



//AJAX ---------------------------------
var request = null;
  function createRequest() {
    try {
      request = new XMLHttpRequest();
    } catch (trymicrosoft) {
      try {
        request = new ActiveXObject("Msxml2.XMLHTTP");
      } catch (othermicrosoft) {
        try {
          request = new ActiveXObject("Microsoft.XMLHTTP");
        } catch (failed) {
          request = null;
        }
      }
    }
    if (request == null)
      alert("Erro na requisição.");
  }
  
function getDigital() {
	var digital = document.getElementById("matricula").value;
	createRequest();
	var url = "/entrada_{"+digital+"}";
	request.open("POST", url, true);
	request.onreadystatechange = atualizaPagina;
	request.send(null);
}
function atualizaPagina() {
	if (request.readyState == 4) {
		var respostaDoServidor = request.responseText;
		json_Digital(respostaDoServidor);
	}
}
function json_Digital(json) {
	var result = json;
	if(result == 'Você já veio hoje.'){
		modalPopup(result)
		document.getElementById("labelMsg").innerHTML = result;
		document.getElementById("icone").innerHTML = '<span style="color:red;font-size:100px" class="fa fa-times-circle"></span>';
	} else if (result == 'Usuário não cadastrado.'){
		modalPopup(result)
		document.getElementById("labelMsg").innerHTML = result;
		document.getElementById("icone").innerHTML = '<span style="color:red;font-size:100px" class="fa fa-times-circle"></span>';
	} else{
		modalPopup(result)
		document.getElementById("labelMsg").innerHTML = result;
		document.getElementById("icone").innerHTML = '<span style="color:green;font-size:100px" style="color:red" class="fa fa-times-circle"></span>';
	}
	requisicoes = 0;
	
	
	//Fechar mensagem
	setTimeout(function() {
		$('.close').click(); 
		document.getElementById("matricula").value = '';
		document.getElementById("matricula").focus();
		document.getElementById("labelMsg").innerHTML = '<br>Insira a sua digital abaixo:';
		document.getElementById("icone").innerHTML = '<img src="assets/images/user.png" style="min-width:200px; min-height:200px; max-width:200px;max-height:200px;align:center;border-radius:50%;" class="card-img-center">';
		}, 2000);
	
	
	
	
}
// AJAX ---------------------------------

</script>

<!-- start: page -->
<form action="/entrada" id="entrada" method="post" accept-charset="utf-8">
	<div class="container">
	  <div class="row" style="padding-top:50px">
	    <div class="col-12 col-sm-12 align-self-center">
	      	<div class="card text-center">
			  <div id="icone" style="padding-top:20px">
			  	<img src="assets/images/user.png" style="min-width:200px; min-height:200px; max-width:200px;max-height:200px;align:center;border-radius:50%;" class="card-img-center">
			  </div>
			  <div class="card-body">
			    <!-- 
			    <h3 class="card-title text-center">BEM-VINDO!</h3>
			    <h4 id="labelMsg" class="card-text text-center"><br>Insira a sua digital abaixo:</h4>
			    
			    <span onclick="getDigital('123')" id="digital" class="card">
			    	<span id="icone"> <br><br><br><br> </span>
			    </span>
			     -->
			     
			    <h3 class="card-title text-center">BEM-VINDO!</h3>
			    <h4 id="labelMsg" class="card-text text-center"><br>Insira a sua matrícula:</h4>
			    <span id="digital">
			    	<input autofocus type="number" id="matricula" name="matricula" class="form-control  text-center" placeholder="Matrícula" />
			    	<br>
			    	<button style="width:50%" onclick="getDigital()" type="button" class="btn btn-primary" data-dismiss="modal">Entrar</button>
			    </span>
			    
			    
			  </div>
			</div>
	    </div>
	  </div>
	</div>
	<input type="hidden" name="acao" value="salvar" />
</form>


