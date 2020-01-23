package br.med.maisvida.service;

import java.util.List;

import br.med.maisvida.entity.Grupo;

public interface GrupoService {

	Grupo buscarPorCodigoExtendido(String codigo);

	List<Grupo> buscarTodos();
	
	List<Grupo> buscarPorCapitulo(Long idCapitulo);

}
