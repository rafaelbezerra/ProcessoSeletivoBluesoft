<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="http://getbootstrap.com/dist/css/bootstrap.css" rel="stylesheet">
		<title>Vote no filme - Votando!</title>
		<script type="text/javascript">
			function verificarSelecionado(){
				if(document.frmVotar.rdoFilme[0].checked == true || document.frmVotar.rdoFilme[1].checked == true){
					document.frmVotar.submit();
				}else{
					alert("Nenhum filme selecionado. Selecione um filme e clique em Votar!");
				}
			}
		</script>
		<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
		<script>
            $(document).ready(function(){   
            	$("#a").fadeOut(0); 
            	$("#b").fadeOut(0);
            	$("#c").fadeOut(0); 
            	$("#a").fadeIn(1000);
	    		$("#b").fadeIn(2000);
	    		$("#c").fadeIn(3000);
	        });
		</script>
	</head>
	<body>
		<%
		if(request.getSession().getAttribute("usu_id") == null){
			response.sendRedirect("index.jsp");
			return;
		}
		%>
		<form name="frmVotar" method="post" action="computar.do">
			<div class="container">
			<hr>
				<div class="jumbotron">
					<h1 id="a">Vote no filme</h1>
					<p id="b">Escolha um filme e clique em votar.</p>
				</div>
	           	<div id="c" class="panel panel-default">				
					<div align="center" class="row">
	            		<div class="col-6 col-sm-5 col-lg-5">
	              			<h2>${sessionScope.fil_nome1}</h2>
	              			<p><input type="radio" name="rdoFilme" value="${sessionScope.fil_id1}"> Vote neste filme</p>
	            		</div>
	            		<div class="col-2 col-sm-2 col-lg-2">
	              			<h2>X</h2>
	            		</div>
	            		<div class="col-6 col-sm-5 col-lg-5">
	              			<h2>${sessionScope.fil_nome2}</h2>
	              			<p><input type="radio" name="rdoFilme" value="${sessionScope.fil_id2}"> Vote neste filme</p>
	           			</div>
	           		</div>
	           		<div align="center" class="row">
						<div class="col-4 col-sm-12 col-lg-4">
	          				<p><a onclick="verificarSelecionado();" class="btn btn-lg btn-primary">Votar!</a></p>
          				</div>	           		
	           		</div>
           		</div>
          		<hr>
			</div>
		</form>
	</body>
</html>