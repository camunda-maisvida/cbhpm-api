package br.med.maisvida.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.med.maisvida.entity.SubGrupo;
import br.med.maisvida.repository.SubGrupoRepositorio;
import br.med.maisvida.service.SubGrupoService;

@Service
@Transactional(readOnly = true)
public class SubGrupoServiceImpl implements SubGrupoService {

	@Autowired
	private SubGrupoRepositorio repositorio;

	@Override
	public SubGrupo buscarPorCodigoExtendido(String codigo) {

		SubGrupo resultado = null;
		if (codigo != null) {
			resultado = this.repositorio.findByCodigoExtendido(codigo);
		}
		return resultado;
	}

	@Override
	public List<SubGrupo> buscarTodos() {

		final List<SubGrupo> resultado = repositorio.findAll();
		return resultado;
	}

	@Override
	public List<SubGrupo> buscarPorGrupo(Long idGrupo) {

		List<SubGrupo> resultado = new ArrayList<>();
		if (idGrupo != null && idGrupo > 0) {
			resultado = this.repositorio.buscarPorGrupo(idGrupo);
		}
		return resultado;
	}

}
