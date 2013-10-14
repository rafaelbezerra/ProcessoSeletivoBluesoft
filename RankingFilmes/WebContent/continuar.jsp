<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="http://getbootstrap.com/dist/css/bootstrap.css" rel="stylesheet">
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
		<title>Vote no filme - Continuar?</title>
	</head>
	<body>
		<%
		if(request.getSession().getAttribute("usu_id") == null){
			response.sendRedirect("index.jsp");
			return;
		}
		%>
		<div class="container">
			<hr>
			<div class="jumbotron">
				<h1 id="a">Vote no filme</h1>
				<p id="b" class="lead">Deseja continuar votando?</p>
				<p>
					<form id="c" method="post" action="continuarVotando.do">
						<a onclick="submit();" class="btn btn-lg btn-info">Continuar Votando</a>
						<a href="finalizar.jsp" class="btn btn-lg btn-primary">Encerrar Votação</a>	
					</form>
				</p>
			</div>
			<hr>
		</div>
	</body>
</html>