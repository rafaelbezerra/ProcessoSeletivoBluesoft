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
		<title>Vote no filme</title>
	</head>
	<body>
		<form method="post" action="iniciar.do">
			<div class="container">
				<hr>
				<div class="jumbotron">
					<h1 id="a">Vote no filme</h1>
					<p id="b" class="lead">Bem vindo ao <strong>Vote no filme</strong>! Aqui nós realizamos o levantamento dos filmes mais votados pelos entusiastas em cinema.</p>
					<p id="c"><a onclick="submit();" class="btn btn-lg btn-primary">Iniciar Votação</a></p>
				</div>
				<hr>
			</div>
		</form>
	</body>
</html>