package br.com.rankingfilmes.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.rankingfilmes.bd.dao.impl.UsuarioDaoImpl;
import br.com.rankingfilmes.model.Usuario;

public class IniciarVotacao extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		Usuario usuario = new Usuario();
		UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();
		
		usuarioDao.salvar(usuario);
		
		usuario.setNome("NomeNãoDefinido" + usuario.getId());
		usuario.setEmail("EmailNãoDefinido" + usuario.getId());
		
		usuarioDao.atualizar(usuario);
		
		HttpSession sessao = request.getSession();
		sessao.setAttribute("usu_id", usuario.getId());
		
		getServletContext().getRequestDispatcher("/continuarVotando.do").forward(request, response);
	}
}