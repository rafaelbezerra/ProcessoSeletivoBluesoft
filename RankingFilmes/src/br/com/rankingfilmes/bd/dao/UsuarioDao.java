package br.com.rankingfilmes.bd.dao;

import java.util.HashMap;
import java.util.List;

import br.com.rankingfilmes.model.Usuario;

public interface UsuarioDao {
	public void salvar(Usuario usuario);
	public void atualizar(Usuario usuario);
	public void excluir(Usuario usuario);
	public Usuario buscarUsuario(Integer id);
	public List<Usuario> listar();
	public HashMap<String, Integer> contarVotosUsuarios();
}
