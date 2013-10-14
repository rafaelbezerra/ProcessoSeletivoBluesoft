package br.com.rankingfilmes.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.rankingfilmes.bd.dao.impl.VotoDaoImpl;
import br.com.rankingfilmes.model.Filme;
import br.com.rankingfilmes.model.Usuario;
import br.com.rankingfilmes.model.Voto;

public class ComputarVoto extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession sessao = request.getSession();
		if(sessao.getAttribute("usu_id") != null){
			Voto voto = new Voto();
			VotoDaoImpl votoDao = new VotoDaoImpl();
			
			Usuario usuario = new Usuario();
			usuario.setId((Integer) sessao.getAttribute("usu_id"));
			
			Filme filme = new Filme();
			filme.setId(Integer.parseInt(request.getParameter("rdoFilme")));
			
			voto.setHora(new Time(System.currentTimeMillis()));
			voto.setData(new Date(System.currentTimeMillis()));
			voto.setFilme(filme);
			voto.setUsuario(usuario);
			
			votoDao.salvar(voto);
			
			response.sendRedirect("continuar.jsp");
		}else{
			response.sendRedirect("index.jsp");
		}
	}
}