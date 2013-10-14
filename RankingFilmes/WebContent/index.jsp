<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="http://getbootstrap.com/dist/css/bootstrap.css" rel="stylesheet">
		<title>Vote no filme</title>
	</head>
	<body>
		<form method="post" action="iniciar.do">
			<div class="container">
				<hr>
				<div class="jumbotron">
					<h1>Vote no filme</h1>
					<p class="lead">Bem vindo ao Vote no filme! Aqui nós realizamos o levantamento dos filmes mais votados pelos entusiastas em cinema.</p>
					<p><a onclick="submit();" class="btn btn-lg btn-success">Iniciar votação</a></p>
				</div>
				<hr>
			</div>
		</form>
	</body>
</html>