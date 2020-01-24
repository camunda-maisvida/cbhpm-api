package br.med.maisvida.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.med.maisvida.entity.Grupo;
import br.med.maisvida.repository.GrupoRepositorio;
import br.med.maisvida.service.GrupoService;

@Service
@Transactional(readOnly = true)
public class GrupoServiceImpl implements GrupoService {

	@Autowired
	private GrupoRepositorio repositorio;

	@Override
	public Grupo buscarPorCodigoExtendido(String codigo) {
		Grupo resultado = null;
		if (codigo != null) {
			resultado = this.repositorio.findByCodigoExtendido(codigo);
		}
		return resultado;
	}

	@Override
	public List<Grupo> buscarTodos() {
		final List<Grupo> resultado = repositorio.findAll();
		return resultado;
	}

	@Override
	public List<Grupo> buscarPorCapitulo(Long idCapitulo) {
		List<Grupo> resultado = new ArrayList<>();
		if(idCapitulo != null && idCapitulo > 0) {
			resultado = this.repositorio.buscarPorCapitulo(idCapitulo);
		}
		return resultado;
	}

}
