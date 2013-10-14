package br.com.rankingfilmes.bd.dao;

import java.util.HashMap;
import java.util.List;
import br.com.rankingfilmes.model.Filme;

public interface FilmeDao {
	public void salvar(Filme filme);
	public void atualizar(Filme filme);
	public void excluir(Filme filme);
	public Filme buscarFilme(Integer id);
	public List<Filme> listar();
	public Filme sortearFilme(Integer id);
	public HashMap<String, Integer> contarVotosFilmes();
}
