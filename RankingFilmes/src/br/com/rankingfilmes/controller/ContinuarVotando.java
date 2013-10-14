package br.com.rankingfilmes.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.rankingfilmes.bd.dao.impl.FilmeDaoImpl;
import br.com.rankingfilmes.model.Filme;

public class ContinuarVotando extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession sessao = request.getSession();
		if(sessao.getAttribute("usu_id") != null){
			Filme filme1 = null;
			Filme filme2 = null;
			FilmeDaoImpl filmeDao = new FilmeDaoImpl();
				
			filme1 = filmeDao.sortearFilme(-1);
			filme2 = filmeDao.sortearFilme(filme1.getId());
			if (filme1 != null && filme2 != null){
				sessao.setAttribute("fil_id1", filme1.getId());
				sessao.setAttribute("fil_nome1", filme1.getNome());
				sessao.setAttribute("fil_id2", filme2.getId());
				sessao.setAttribute("fil_nome2", filme2.getNome());
				response.sendRedirect("votar.jsp");
			}else{
				response.sendRedirect("index.jsp");
			}
		}else{
			response.sendRedirect("index.jsp");
		}
	}
}