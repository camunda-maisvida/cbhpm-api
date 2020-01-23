package br.med.maisvida.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.med.maisvida.entity.Capitulo;
import br.med.maisvida.repository.CapituloRepositorio;
import br.med.maisvida.service.CapituloService;

@Service
@Transactional(readOnly = true)
public class CapituloServiceImpl implements CapituloService {

	@Autowired
	private CapituloRepositorio repositorio;

	@Override
	public Capitulo buscarPorCodigo(Integer codigo) {
		Capitulo resultado = null;
		if (codigo != null) {
			resultado = this.repositorio.findByCodigo(codigo);
		}
		return resultado;
	}

	@Override
	public List<Capitulo> buscarTodos() {
		final List<Capitulo> resultado = repositorio.findAll();
		return resultado;
	}
	
	@Override
	public List<Capitulo> buscarPorTabela(Long idTabela) {
		List<Capitulo> resultado = new ArrayList<>();
		if(idTabela != null && idTabela > 0) {
			resultado = this.repositorio.buscarPorTabela(idTabela);
		}
		return resultado;
	}

}
