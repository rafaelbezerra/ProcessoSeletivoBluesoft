<%@ page import="java.util.HashMap, java.util.Iterator" %>
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
	    		$("#b").fadeIn(4000);
	    		$("#c").fadeIn(7000);
	        });
		</script>
		<title>Vote no filme - Resultado!</title>
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
			<div id="a" class="jumbotron">
				<h1>Vote no filme</h1>
				<p class="lead">${sessionScope.usu_nome}, obrigado por participar do Vote no filme!</p>
				<a href="index.jsp" class="btn btn-lg btn-info">Voltar a tela inicial</a>
			</div>
			<h2 id="b">Ranking de votos em filmes:</h2>
			<div id="b" class="panel panel-default">
  				<div class="panel-body">
			<%
			HashMap<String, Integer> votosFilmes = (HashMap) request.getSession().getAttribute("votosFilmes");
			if(votosFilmes != null){
				Iterator<String> keySetIterator1 = votosFilmes.keySet().iterator();
				while(keySetIterator1.hasNext()){
		  			String filme = keySetIterator1.next();
		  			if(!filme.equals("total")){
			  			Double parcial = Double.parseDouble(votosFilmes.get(filme) + " ");
			  			Double total = Double.parseDouble(votosFilmes.get("total") + " ");
			  			Integer porc = (int) (((parcial / total) * 100) - (((parcial / total) * 100) % 1));
		  			%>
			  		<p><%= filme + " | " + porc + "% | " + votosFilmes.get(filme) + " Voto(s)" %></p>
					<div class="progress">
						<div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="<%= votosFilmes.get(filme) %>" aria-valuemin="0" aria-valuemax="<%= votosFilmes.get("total") %>" style="width: <%= porc %>%"></div>
					</div>
					<%
		  			}
			  	}
			}else{
				response.sendRedirect("index.jsp");
			}
			%>
				</div>
			</div>
			
			<h2 id="c">Ranking de votos por usuário:</h2>
			<div id="c" class="panel panel-default">
  				<div class="panel-body">
			<%
			HashMap<String, Integer> votosUsuarios = (HashMap) request.getSession().getAttribute("votosUsuarios");
			request.getSession().removeAttribute("usu_id");
			request.getSession().invalidate();
			Iterator<String> keySetIterator2 = votosUsuarios.keySet().iterator();
			while(keySetIterator2.hasNext()){
	  			String usuario = keySetIterator2.next();
	  			if(!usuario.equals("total")){
		  			Double parcial = Double.parseDouble(votosUsuarios.get(usuario) + " ");
		  			Double total = Double.parseDouble(votosUsuarios.get("total") + " ");
		  			Integer porc = (int) (((parcial / total) * 100) - (((parcial / total) * 100) % 1));
		  			%>
			  		<p><%= usuario + " | " + porc + "% | " + votosUsuarios.get(usuario) + " Voto(s)" %></p>
					<div class="progress">
						<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="<%= votosUsuarios.get(usuario) %>" aria-valuemin="0" aria-valuemax="<%= votosUsuarios.get("total") %>" style="width: <%= porc %>%"></div>
					</div>
					<%
	  			}
		  	}
			%>
				</div>
			</div>
			<hr>
		</div>
	</body>
</html>