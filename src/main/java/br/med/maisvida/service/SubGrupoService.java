package br.med.maisvida.service;

import java.util.List;

import br.med.maisvida.entity.SubGrupo;

public interface SubGrupoService {

	SubGrupo buscarPorCodigoExtendido(String codigo);

	List<SubGrupo> buscarTodos();

	List<SubGrupo> buscarPorGrupo(Long idGrupo);

}
