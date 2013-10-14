<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="http://getbootstrap.com/dist/css/bootstrap.css" rel="stylesheet">
		<script type="text/javascript">
			function validarCampos(){
				if(document.frmCadastro.txtNome.value != "" &&
						document.frmCadastro.txtEmail.value != "" &&
						document.frmCadastro.txtConfirmarEmail.value != ""){
					if(document.frmCadastro.txtEmail.value == document.frmCadastro.txtConfirmarEmail.value){
						document.frmCadastro.submit();
					}else{
						alert("E-mail não confere com o confirmado, por favor verifique e tente novamente.");
					}
				}else{
					alert("É necessário o preenchimento de todos os campos, preencha-os e tente novamente.");
				}
			}
		</script>
		<title>Vote no filme - Finalizando...</title>
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
				<h1>Vote no filme</h1>
				<p class="lead">Informe o seu nome e e-mail para que seja exibido o resultado.</p>
				<form name="frmCadastro" method="post" action="exibirResultados.do">
					<div class="input-group">
						<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
 							<input type="text" name="txtNome" maxlength="50" class="form-control" placeholder="Nome">
					</div>
					<div class="input-group">
 							<span class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span></span>
 							<input type="text" name="txtEmail" maxlength="50" class="form-control" placeholder="E-mail">
					</div>
					<div class="input-group">
 							<span class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span></span>
 							<input type="text" name="txtConfirmarEmail" maxlength="50" class="form-control" placeholder="Confirmar e-mail">
					</div>
					<br>
					<a onclick="validarCampos();" class="btn btn-lg btn-success">Exibir resultados!</a>
				</form>
			</div>
			<hr>
		</div>
	</body>
</html>