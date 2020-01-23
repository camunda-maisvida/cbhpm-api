package br.med.maisvida.service;

import java.util.List;

import br.med.maisvida.entity.Procedimento;
import br.med.maisvida.rest.dto.ParametroConsultaProcedimentoDTO;
import br.med.maisvida.rest.dto.ProcedimentoDTO;

public interface ProcedimentoService {

	Procedimento buscarPorCodigoExtendido(String codigo);

	List<Procedimento> buscarTodos(Integer page, Integer size);

	List<Procedimento> buscarPorParametro(ParametroConsultaProcedimentoDTO parametro);

	List<ProcedimentoDTO> buscarDTOPorParametro(ParametroConsultaProcedimentoDTO parametro);

}
