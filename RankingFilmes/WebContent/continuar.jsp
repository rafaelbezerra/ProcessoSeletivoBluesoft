<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="http://getbootstrap.com/dist/css/bootstrap.css" rel="stylesheet">
		<title>Vote no filme - Continuar?</title>
	</head>
	<body>
		<%
		if(request.getSession().getAttribute("usu_id") == null){
			response.sendRedirect("index.jsp");			
		}
		%>
		<div class="container">
			<hr>
			<div class="jumbotron">
				<h1>Vote no filme</h1>
				<p class="lead">Deseja continuar votando?</p>
				<p>
					<form method="post" action="continuarVotando.do">
						<a onclick="submit();" class="btn btn-lg btn-success">Continuar Votando</a>
						<a href="finalizar.jsp" class="btn btn-lg btn-success">Encerrar Votação</a>	
					</form>
				</p>
			</div>
			<hr>
		</div>
	</body>
</html>