package br.com.rankingfilmes.bd.dao.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.rankingfilmes.bd.conexao.HibernateUtil;
import br.com.rankingfilmes.bd.dao.FilmeDao;
import br.com.rankingfilmes.model.Filme;
import br.com.rankingfilmes.model.Voto;

public class FilmeDaoImpl implements FilmeDao{
	private Session	sessao;
	private Transaction	transacao;

	public void salvar(Filme filme) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.save(filme);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível inserir o filme. Erro: " + e.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de inserção. Mensagem: " + e.getMessage());
			}
		}
	}

	public void atualizar(Filme filme) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.update(filme);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível alterar o filme. Erro: " + e.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de atualização. Mensagem: " + e.getMessage());
			}
		}
	}

	public void excluir(Filme filme) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.delete(filme);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível excluir o filme. Erro: " + e.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de exclusão. Mensagem: " + e.getMessage());
			}
		}
	}

	public Filme buscarFilme(Integer id) {
		Filme filme = null;

		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Filme.class);
			filtro.add(Restrictions.eq("id", id));
			filme = (Filme) filtro.uniqueResult();
			this.transacao.commit();
		} catch (Throwable e) {
			if (this.transacao.isActive()) {
				this.transacao.rollback();
			}
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de busca. Mensagem: " + e.getMessage());
			}
		}
		return filme;
	}

	public List<Filme> listar() {
		List<Filme> filmes = null;

		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Filme.class);
			filmes = filtro.list();
			this.transacao.commit();
		} catch (Throwable e) {
			if (this.transacao.isActive()) {
				this.transacao.rollback();
			}
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de listagem. Mensagem: " + e.getMessage());
			}
		}
		return filmes;
	}
	
	public Filme sortearFilme(Integer id){
		try {
			Filme filme = null;
			Random gerador = new Random();
			while (true){
				filme = this.buscarFilme(gerador.nextInt(this.listar().size()) + 1);
				if(filme != null){
					if(!filme.getId().equals(id)){
						return filme;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Erro ao realizar sorteio de filme. Erro: " + e.getMessage());
			return null;
		}
	}
	
	public HashMap<String, Integer> contarVotosFilmes(){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			
			Criteria filtro = sessao.createCriteria(Voto.class); 
			filtro.setProjection(Projections.projectionList().add(Projections.groupProperty("filme.id")).add(Projections.count("filme").as("contador")));
			filtro.addOrder(Order.desc("contador"));
			
			List resultado = filtro.list();
			this.transacao.commit();
			
			Integer total = 0;
			for(Object a : resultado){
				String str = Arrays.deepToString((Object[]) a);
				str = str.replace("[", "");
				str = str.replace("]", "");
				String[] l = str.split(", "); 

				total += Integer.parseInt(l[1]);
				
				map.put(this.buscarFilme(Integer.parseInt(l[0])).getNome(), Integer.parseInt(l[1]));
			}
			map.put("total", total);
		} catch (Throwable e) {
			if (this.transacao.isActive()) {
				this.transacao.rollback();
			}
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de contar votos de filmes. Mensagem: " + e.getMessage());
			}
		}
		return map;
	}
}