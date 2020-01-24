package br.med.maisvida.service;

import java.util.List;

import br.med.maisvida.entity.Tabela;

public interface TabelaService {

	Tabela buscarPorCodigo(Integer codigo);

	List<Tabela> buscarTodos();

}
