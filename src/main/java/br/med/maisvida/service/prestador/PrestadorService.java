package br.med.maisvida.service.prestador;

import java.util.List;

import br.med.maisvida.entity.prestador.Prestador;
import br.med.maisvida.rest.dto.prestador.NotificarRejeicaoDTO;
import br.med.maisvida.rest.dto.prestador.PrestadorDTO;
import br.med.maisvida.rest.dto.prestador.PrestadorProcedimentoDTO;
import br.med.maisvida.rest.dto.prestador.PrestadorResultDTO;

public interface PrestadorService {

	PrestadorDTO salvarRetornandoDTO(PrestadorDTO prestadorParaSalvar);

	Prestador salvar(Prestador entidade);

	List<Prestador> buscarTodos(Integer page, Integer size);

	Prestador buscarPoID(Long id);

	PrestadorResultDTO atualizarProcedimentos(PrestadorProcedimentoDTO prestadorProcedimento);

	PrestadorDTO buscarDTOPorCnpj(String cnpj);

	boolean notificarRejeicao(NotificarRejeicaoDTO rejeicao);

}
