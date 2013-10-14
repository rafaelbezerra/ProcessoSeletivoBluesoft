package br.com.rankingfilmes.bd.dao;

import java.util.List;

import br.com.rankingfilmes.model.Voto;

public interface VotoDao {
	public void salvar(Voto voto);
	public void atualizar(Voto voto);
	public void excluir(Voto voto);
	public Voto buscarVoto(Integer id);
	public List<Voto> listar();
}
