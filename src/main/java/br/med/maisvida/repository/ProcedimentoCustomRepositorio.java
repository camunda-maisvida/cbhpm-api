package br.med.maisvida.repository;

import java.util.List;

import br.med.maisvida.entity.Procedimento;
import br.med.maisvida.rest.dto.ParametroConsultaProcedimentoDTO;
import br.med.maisvida.rest.dto.ProcedimentoDTO;

public interface ProcedimentoCustomRepositorio {

	List<Procedimento> buscarPorParametro(ParametroConsultaProcedimentoDTO parametro);

	List<ProcedimentoDTO> buscarDTOPorParametro(ParametroConsultaProcedimentoDTO parametro);
}
