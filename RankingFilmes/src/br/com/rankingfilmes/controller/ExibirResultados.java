package br.com.rankingfilmes.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.rankingfilmes.bd.dao.impl.FilmeDaoImpl;
import br.com.rankingfilmes.bd.dao.impl.UsuarioDaoImpl;
import br.com.rankingfilmes.model.Usuario;

public class ExibirResultados extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession sessao = request.getSession();
		if(sessao.getAttribute("usu_id") != null){
			Usuario usuario = new Usuario();
			UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();
			
			usuario.setId((Integer) sessao.getAttribute("usu_id"));
			usuario.setNome(request.getParameter("txtNome").trim());
			sessao.setAttribute("usu_nome", request.getParameter("txtNome").trim());
			
			if(request.getParameter("txtEmail") == request.getParameter("txtConfirmarEmail")){
				usuario.setEmail(request.getParameter("txtEmail").trim());
			}
			
			usuarioDao.atualizar(usuario);
			
			FilmeDaoImpl filmeDao = new FilmeDaoImpl();
			
			HashMap<String, Integer> votosFilmes = filmeDao.contarVotosFilmes();
			sessao.setAttribute("votosFilmes", votosFilmes);

			HashMap<String, Integer> votosUsuarios = usuarioDao.contarVotosUsuarios();
			sessao.setAttribute("votosUsuarios", votosUsuarios);
					
			response.sendRedirect("resultado.jsp");
		}else{
			response.sendRedirect("index.jsp");
		}
	}
}