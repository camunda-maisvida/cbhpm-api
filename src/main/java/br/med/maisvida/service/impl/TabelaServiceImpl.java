package br.med.maisvida.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.med.maisvida.entity.Tabela;
import br.med.maisvida.repository.TabelaRepositorio;
import br.med.maisvida.service.TabelaService;

@Service
@Transactional(readOnly = true)
public class TabelaServiceImpl implements TabelaService {

	@Autowired
	private TabelaRepositorio repositorio;

	@Override
	public Tabela buscarPorCodigo(Integer codigo) {
		Tabela resultado = null;
		if (codigo != null) {
			resultado = this.repositorio.findByCodigo(codigo);
		}
		return resultado;
	}

	@Override
	public List<Tabela> buscarTodos() {
		final List<Tabela> resultado = repositorio.findAll();
		return resultado;
	}

}
