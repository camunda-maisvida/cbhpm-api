package br.med.maisvida.service.prestador;

import java.io.FileNotFoundException;
import java.util.List;

import com.itextpdf.text.DocumentException;

import br.med.maisvida.entity.prestador.Prestador;
import br.med.maisvida.rest.dto.ArquivoDTO;
import br.med.maisvida.rest.dto.prestador.CnpjComMotivoDTO;
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

	boolean notificarRejeicao(CnpjComMotivoDTO rejeicao);

	void gerarContrato(String cnpj, String observacao) throws FileNotFoundException, DocumentException;

	String recuperarContrato(String cnpj);

	void uploadContratoAssinado(String cnpj, ArquivoDTO arquivo);

}
