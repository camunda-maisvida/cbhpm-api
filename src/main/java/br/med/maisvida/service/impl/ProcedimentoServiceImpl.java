package br.med.maisvida.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.med.maisvida.entity.Procedimento;
import br.med.maisvida.repository.ProcedimentoRepositorio;
import br.med.maisvida.rest.dto.ParametroConsultaProcedimentoDTO;
import br.med.maisvida.rest.dto.ProcedimentoDTO;
import br.med.maisvida.service.ProcedimentoService;

@Service
@Transactional(readOnly = true)
public class ProcedimentoServiceImpl implements ProcedimentoService {

	@Autowired
	private ProcedimentoRepositorio repositorio;

	@Override
	public Procedimento buscarPorCodigoExtendido(String codigo) {

		Procedimento resultado = null;
		if (codigo != null) {
			resultado = this.repositorio.findByCodigoExtendido(codigo);
		}
		return resultado;
	}

	@Override
	public List<Procedimento> buscarTodos(Integer page, Integer size) {

		List<Procedimento> resultado = new ArrayList<>();

		if (temNumeroValido(page) && temNumeroValido(size)) {

			Pageable paginacao = PageRequest.of(page, size);

			final Page<Procedimento> paginaResult = repositorio.findAll(paginacao);

			resultado = paginaResult.getContent();
		} else {
			resultado = repositorio.findAll();
		}
		return resultado;
	}

	@Override
	public List<Procedimento> buscarPorParametro(ParametroConsultaProcedimentoDTO parametro) {

		List<Procedimento> resultado = new ArrayList<>();
		if (parametro != null) {
			resultado = repositorio.buscarPorParametro(parametro);
		}
		return resultado;
	}

	@Override
	public List<ProcedimentoDTO> buscarDTOPorParametro(ParametroConsultaProcedimentoDTO parametro) {

		List<ProcedimentoDTO> resultado = new ArrayList<>();
		if (parametro != null) {
			resultado = repositorio.buscarDTOPorParametro(parametro);
		}
		return resultado;
	}
	
	//TODO extrair para Utilitario
	private boolean temNumeroValido(Integer valor) {
		return valor != null && valor >= 0;
	}

}
