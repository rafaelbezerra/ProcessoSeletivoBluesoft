package br.com.rankingfilmes.bd.dao.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.rankingfilmes.bd.conexao.HibernateUtil;
import br.com.rankingfilmes.bd.dao.UsuarioDao;
import br.com.rankingfilmes.model.Usuario;
import br.com.rankingfilmes.model.Voto;

public class UsuarioDaoImpl implements UsuarioDao{
	private Session	sessao;
	private Transaction	transacao;

	public void salvar(Usuario usuario) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.save(usuario);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível inserir o usuario. Erro: " + e.getMessage());
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

	public void atualizar(Usuario usuario) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.update(usuario);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível alterar o usuario. Erro: " + e.getMessage());
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

	public void excluir(Usuario usuario) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.delete(usuario);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível excluir o usuario. Erro: " + e.getMessage());
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

	public Usuario buscarUsuario(Integer id) {
		Usuario usuario = null;

		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Usuario.class);
			filtro.add(Restrictions.eq("id", id));
			usuario = (Usuario) filtro.uniqueResult();
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
		return usuario;
	}

	public List<Usuario> listar() {
		List<Usuario> usuarios = null;

		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Usuario.class);
			usuarios = filtro.list();
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
		return usuarios;
	}
	
	public HashMap<String, Integer> contarVotosUsuarios(){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		try {	
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			
			Criteria filtro = sessao.createCriteria(Voto.class); 
			filtro.setProjection(Projections.projectionList().add(Projections.groupProperty("usuario.id")).add(Projections.count("usuario").as("contador")));
			filtro.addOrder(Order.desc("contador"));
			
			List resultado = filtro.list();
			this.transacao.commit();
			
			Integer total = 0;
			for(Object a : resultado){
				String str = Arrays.deepToString((Object[]) a);
				str = str.replace("[", "");
				str = str.replace("]", "");
				String[] l = str.split(", "); 

				String nomeUsuario = this.buscarUsuario(Integer.parseInt(l[0])).getNome();
				Integer votos = Integer.parseInt(l[1]);
				
				total += votos;
				
				if(map.containsKey(nomeUsuario)){
					Integer votosAnteriores = map.get(nomeUsuario);
					map.remove(nomeUsuario);
					map.put(nomeUsuario, votos + votosAnteriores);
				}else{
					map.put(nomeUsuario, votos);
				}
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
				System.out.println("Erro ao fechar operação de contar votos de usuários. Mensagem: " + e.getMessage());
			}
		}
		return map;
	}	
}