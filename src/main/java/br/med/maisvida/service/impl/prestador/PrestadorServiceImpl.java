package br.med.maisvida.service.impl.prestador;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.med.maisvida.entity.prestador.Prestador;
import br.med.maisvida.repository.prestador.PrestadorProcedimentoRepositorio;
import br.med.maisvida.repository.prestador.PrestadorRepositorio;
import br.med.maisvida.rest.dto.prestador.PrestadorDTO;
import br.med.maisvida.rest.dto.prestador.PrestadorFullResultDTO;
import br.med.maisvida.rest.dto.prestador.PrestadorProcedimentoDTO;
import br.med.maisvida.rest.dto.prestador.PrestadorProcedimentoItemDTO;
import br.med.maisvida.rest.dto.prestador.PrestadorResultDTO;
import br.med.maisvida.service.cnes.CnesService;
import br.med.maisvida.service.prestador.PrestadorService;

@Service
@Transactional(readOnly = true)
public class PrestadorServiceImpl implements PrestadorService {

	@Autowired
	private PrestadorRepositorio repositorio;

	@Autowired
	private CnesService cnesService;

	@Autowired
	private PrestadorProcedimentoRepositorio prestadorProcedimentoRepositorio;

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly = false)
	public PrestadorDTO salvarRetornandoDTO(@Valid PrestadorDTO prestadorParaSalvar) {

		if (prestadorParaSalvar != null) {
			Prestador prestadorParaSalvarOuAtualizar = new Prestador();
			if (prestadorParaSalvar.getId() != null && prestadorParaSalvar.getId() > 0) {
				prestadorParaSalvarOuAtualizar = this.repositorio.findById(prestadorParaSalvar.getId())
						.orElseThrow(() -> new IllegalStateException("Prestador não encontrado com o id: " + prestadorParaSalvar.getId()));
			}

			BeanUtils.copyProperties(prestadorParaSalvar, prestadorParaSalvarOuAtualizar);
			BeanUtils.copyProperties(prestadorParaSalvar.getEndereco(), prestadorParaSalvarOuAtualizar.getEndereco());
			Prestador prestadorSalvo = this.repositorio.save(prestadorParaSalvarOuAtualizar);
			
			flushAndClear();
			return new PrestadorResultDTO(this.repositorio.findById(prestadorSalvo.getId()).get());
		}
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public Prestador salvar(Prestador entidade) {

		if (entidade != null) {
			return this.repositorio.save(entidade);
		}
		return null;
	}

	@Override
	public List<Prestador> buscarTodos(Integer page, Integer size) {

		List<Prestador> resultado = new ArrayList<>();

		if (temNumeroValido(page) && temNumeroValido(size)) {

			Pageable paginacao = PageRequest.of(page, size);

			final Page<Prestador> paginaResult = repositorio.findAll(paginacao);

			resultado = paginaResult.getContent();
		} else {
			resultado = repositorio.findAll();
		}
		return resultado;
	}

	@Override
	@Transactional(readOnly = false)
	public PrestadorResultDTO atualizarProcedimentos(PrestadorProcedimentoDTO prestadorProcedimento) {

		PrestadorResultDTO result = null;
		if (prestadorProcedimento != null && temNumeroValido(prestadorProcedimento.getId())) {
			Prestador prestador = this.repositorio.findById(prestadorProcedimento.getId()).orElseThrow(() -> new IllegalStateException("Prestador não encontrado com o id: " + prestadorProcedimento.getId()));

			// Sobrepondo os procedimentos
			if (!CollectionUtils.isEmpty(prestadorProcedimento.getProcedimentosSobrepor())) {
				prestador.sobreporProcedimentos(prestadorProcedimento.getProcedimentosSobrepor());
				this.repositorio.save(prestador);
			}
			// Deletando todos os procedimentos
			else if (BooleanUtils.isTrue(prestadorProcedimento.getRemoverTodos())) {
				this.prestadorProcedimentoRepositorio.deletarPorPrestador(prestador.getId());
			} else {
				// Adicionando procedimentos
				if (!CollectionUtils.isEmpty(prestadorProcedimento.getProcedimentosAdicionar())) {
					prestador.adicionarProcedimentos(prestadorProcedimento.getProcedimentosAdicionar());
					this.repositorio.save(prestador);

				}

				// Removendo procedimentos
				if (!CollectionUtils.isEmpty(prestadorProcedimento.getProcedimentosRemover())) {
					this.prestadorProcedimentoRepositorio.deletarPorProcedimentos(prestadorProcedimento.getProcedimentosRemover().stream().mapToLong(PrestadorProcedimentoItemDTO::getIdProcedimento).toArray());
				}
			}
			flushAndClear();
			prestador = this.buscarPoID(prestador.getId());
			result = new PrestadorResultDTO(prestador);
		}
		return result;
	}

	@Override
	public Prestador buscarPoID(Long id) {

		return this.repositorio.findById(id).orElse(null);
	}

	@Override
	public PrestadorDTO buscarDTOPorCnpj(String cnpj) {
		PrestadorDTO result = null;
		if(StringUtils.isNotBlank(cnpj)) {
			final Prestador prestador = this.buscarPorCnpj(Long.valueOf(cnpj));
			if(prestador != null) {
				result = new PrestadorFullResultDTO(prestador);
			}else {
				final String jsonResult = this.cnesService.buscarPorCnpj(cnpj);
				result = getPrestadorDTOFromJsonString(jsonResult);
			}
		}
		return result;
	}

	public Prestador buscarPorCnpj(Long cnpj) {
		return this.repositorio.findByCnpj(cnpj);
		
	}
	
	private PrestadorDTO getPrestadorDTOFromJsonString(String jsonResult) {
		PrestadorDTO result = null;
		if(StringUtils.isNotBlank(jsonResult)) {
			
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				JsonNode readTree = objectMapper.readTree(jsonResult);
				Long cnpjResult = readTree.get("cnpj").get("numeroCNPJ").asLong();
				String codigoCNES = readTree.get("codigoCNES").get("codigo").asText();
				String codigoUnidade = readTree.get("codigoUnidade").get("codigo").asText();
				String nomeFantasia = readTree.get("nomeFantasia").get("nome").asText();
				String nomeEmpresarial = readTree.get("nomeEmpresarial").get("nome").asText();
				
				String email = getValueFromJsonNode(readTree.get("email").get("descricaoEmail"));
				
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
							
				LocalDateTime dataAtualizacao = ZonedDateTime.parse(readTree.get("dataAtualizacao").asText(), formatter).toLocalDateTime();
				
				JsonNode endereco = readTree.get("endereco");
				String logradouro = getValueFromJsonNode(endereco.get("nomeLogradouro"));
				String numero = getValueFromJsonNode(endereco.get("numero"));
				String bairro = getValueFromJsonNode(endereco.get("bairro").get("descricaoBairro"));
				
				JsonNode municipio = readTree.get("endereco").get("municipio");
				String cidade = getValueFromJsonNode(municipio.get("nomeMunicipio"));
				String uf = getValueFromJsonNode(municipio.get("uf").get("siglaUF"));
				
				String cep = getValueFromJsonNode(endereco.get("cep").get("numeroCEP"));
				
				result = new PrestadorDTO();
				result.setCnpj(cnpjResult);
				result.setCodigoCnes(codigoCNES);
				result.setCodigoUnidade(codigoUnidade);
				result.setNomeFantasia(nomeFantasia);
				result.setNomeEmpresarial(nomeEmpresarial);
				result.setEmail(email);
				result.setDtAtualizacao(dataAtualizacao);
				result.getEndereco().setLogradouro(logradouro);
				result.getEndereco().setNumero(numero);
				result.getEndereco().setBairro(bairro);
				result.getEndereco().setCidade(cidade);
				result.getEndereco().setUf(uf);
				result.getEndereco().setCep(cep);
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	private String getValueFromJsonNode(JsonNode jsonNode) {
		if(jsonNode != null) {
			return jsonNode.asText();
		}
		
		return null;
	}
	
	// TODO extrair para Utilitario
	private boolean temNumeroValido(Number valor) {

		return valor != null && valor.longValue() >= 0;
	}

	private void flushAndClear() {
		em.flush();
		em.clear();
	}


}
