package br.med.maisvida.service;

import java.util.List;

import br.med.maisvida.entity.Capitulo;

public interface CapituloService {

	Capitulo buscarPorCodigo(Integer codigo);

	List<Capitulo> buscarTodos();

	List<Capitulo> buscarPorTabela(Long idTabela);

}
