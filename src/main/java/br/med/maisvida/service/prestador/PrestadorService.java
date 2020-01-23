package br.med.maisvida.service.prestador;

import java.util.List;

import br.med.maisvida.entity.prestador.Prestador;
import br.med.maisvida.rest.dto.prestador.PrestadorDTO;

public interface PrestadorService {

	PrestadorDTO salvarRetornandoDTO(PrestadorDTO prestadorParaSalvar);

	Prestador salvar(Prestador entidade);

	List<Prestador> buscarTodos(Integer page, Integer size);

}
