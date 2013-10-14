<%@ page import="java.util.HashMap, java.util.Iterator" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="http://getbootstrap.com/dist/css/bootstrap.css" rel="stylesheet">
		<title>Vote no filme - Resultado!</title>
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
				<p class="lead">${sessionScope.usu_nome}, obrigado por participar do Vote no filme!</p>
			</div>
			<br>
			<h2>Filmes:</h2>
			<div class="panel panel-default">
  				<div class="panel-body">
			<%
			HashMap<String, Integer> votosFilmes = (HashMap) request.getSession().getAttribute("votosFilmes");
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
						<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="<%= votosFilmes.get(filme) %>" aria-valuemin="0" aria-valuemax="<%= votosFilmes.get("total") %>" style="width: <%= porc %>%"></div>
					</div>
					<%
	  			}
		  	}
			%>
				</div>
			</div>
			
			<h2>Usuários:</h2>
			<div class="panel panel-default">
  				<div class="panel-body">
			<%
			HashMap<String, Integer> votosUsuarios = (HashMap) request.getSession().getAttribute("votosUsuarios");
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
						<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="<%= votosUsuarios.get(usuario) %>" aria-valuemin="0" aria-valuemax="<%= votosUsuarios.get("total") %>" style="width: <%= porc %>%"></div>
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