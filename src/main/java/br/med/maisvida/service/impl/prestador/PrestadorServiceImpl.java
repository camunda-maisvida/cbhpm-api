package br.med.maisvida.service.impl.prestador;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
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
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.med.maisvida.entity.prestador.Prestador;
import br.med.maisvida.repository.prestador.PrestadorProcedimentoRepositorio;
import br.med.maisvida.repository.prestador.PrestadorRepositorio;
import br.med.maisvida.rest.dto.ArquivoDTO;
import br.med.maisvida.rest.dto.email.EmailDTO;
import br.med.maisvida.rest.dto.prestador.CnpjComMotivoDTO;
import br.med.maisvida.rest.dto.prestador.PrestadorDTO;
import br.med.maisvida.rest.dto.prestador.PrestadorFullResultDTO;
import br.med.maisvida.rest.dto.prestador.PrestadorProcedimentoDTO;
import br.med.maisvida.rest.dto.prestador.PrestadorProcedimentoItemDTO;
import br.med.maisvida.rest.dto.prestador.PrestadorResultDTO;
import br.med.maisvida.service.cnes.CnesService;
import br.med.maisvida.service.email.EnviadorDeEmailService;
import br.med.maisvida.service.prestador.PrestadorService;

@Service
@Transactional(readOnly = true)
public class PrestadorServiceImpl implements PrestadorService {

	@Autowired
	private PrestadorRepositorio repositorio;

	@Autowired
	private CnesService cnesService;

	// TODO extrair para SERVICE
	@Autowired
	private PrestadorProcedimentoRepositorio prestadorProcedimentoRepositorio;

	@Autowired
	private EnviadorDeEmailService enviadorDeEmailService;

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly = false)
	public PrestadorDTO salvarRetornandoDTO(@Valid PrestadorDTO prestadorParaSalvar) {

		if (prestadorParaSalvar != null) {
			Prestador prestadorParaSalvarOuAtualizar = new Prestador();
			if (prestadorParaSalvar.getId() != null && prestadorParaSalvar.getId() > 0) {
				prestadorParaSalvarOuAtualizar = this.repositorio.findById(prestadorParaSalvar.getId()).orElseThrow(() -> new IllegalStateException("Prestador não encontrado com o id: " + prestadorParaSalvar.getId()));
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
		if (StringUtils.isNotBlank(cnpj)) {
			final Prestador prestador = this.buscarPorCnpj(Long.valueOf(cnpj));
			if (prestador != null) {
				result = new PrestadorFullResultDTO(prestador);
			} else {
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
		if (StringUtils.isNotBlank(jsonResult)) {

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

		if (jsonNode != null) {
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

	@Override
	public boolean notificarRejeicao(CnpjComMotivoDTO rejeicao) {

		if (rejeicao != null && rejeicao.temParametrosPreenchidos()) {
			Prestador prestador = this.buscarPorCnpj(Long.valueOf(rejeicao.getCnpj()));
			StringBuilder texto = new StringBuilder();

			texto.append("Olá <b>").append(prestador.getNomeEmpresarial()).append("</b>!").append("<br>");
			texto.append("Sua solicitação foi analizada e infelizmente foi rejeitada.").append("<br>");
			texto.append("Detalhe:").append("<br>");
			texto.append("<b>").append("CNPJ").append(" :</b> ").append(rejeicao.getCnpj()).append("<br>");
			texto.append("<b>").append("Motivo").append(" :</b> ").append(rejeicao.getMotivo()).append("<br>");
			texto.append("	<b>").append("Procedimentos Selecionados").append(" :</b> ").append("<br>");
			texto.append("<table border=\"1|0\"");
			texto.append("<tr><th>Descrição</th><th>Valor</th><th>Valor Proposto</th></tr>");
			prestador.getPrestadorProcedimentos().stream().forEach(item -> {
				texto.append("<tr>");
				texto.append("<td>").append(item.getProcedimento().getCodigoExtendido() + " - " + item.getProcedimento().getDescricao()).append("</td>");
				texto.append("<td>").append(item.getValor()).append("</td>");
				if(item.getValorProposto() == null) {					
					texto.append("<td>").append("N/A").append("</td>");
				}else {					
					texto.append("<td>").append(item.getValorProposto()).append("</td>");
				}
				texto.append("</tr>");
			});

			texto.append("</table>");
			texto.append("-----------").append("<br>");
			texto.append("<b>Suporte Mais Vida</b>");

			EmailDTO email = EmailDTO.EmailBuilder.newBuild().from("suporte.maisvida@gmail.com").to("rodolfo.maisvida@gmail.com", "wagner.maisvida@gmail.com", "marcio.maisvida@gmail.com").subject("[Notificação] Rejeição de Solicitação Prestação de Serviço").content(texto.toString()).build();
			enviadorDeEmailService.enviar(email);
			return true;
		}
		return false;
	}

	@Override
	public String recuperarContrato(String cnpj) {

		if (StringUtils.isNotBlank(cnpj)) {
			Prestador prestador = this.buscarPorCnpj(Long.valueOf(cnpj));
			if (prestador != null) {
				return prestador.getContratoBase64();
			}
		}

		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public void uploadContratoAssinado(String cnpj, ArquivoDTO arquivo) {

		if (StringUtils.isNotBlank(cnpj) && arquivo != null && StringUtils.isNotBlank(arquivo.getArquivoBase64())) {

			this.repositorio.salvarContratoAssinado(Long.valueOf(cnpj), arquivo.getArquivoBase64());
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void gerarContrato(String cnpj, String observacao) {

		Prestador prestador = this.buscarPorCnpj(Long.valueOf(cnpj));
		try {
			byte[] arquivoGerado = this.gerarArquivoContrato(prestador, observacao);
			String contratoBase64 = Base64.getEncoder().encodeToString(arquivoGerado);
			if (StringUtils.isNotBlank(contratoBase64)) {
				this.repositorio.salvarContrato(prestador.getId(), contratoBase64);
			}
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}

	}

	public byte[] gerarArquivoContrato(Prestador prestador, String observacao) throws FileNotFoundException, DocumentException {

		Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
		Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);

		Document document = new Document();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, baos);

		document.open();

		document.addAuthor("Mais Vida");
		document.addCreationDate();
		document.addTitle("Contrato de Prestador");

		Anchor anchor = new Anchor("Contrato de Prestador", catFont);
		anchor.setName("Contrato de Prestador");

		// Second parameter is the number of the chapter
		Chapter catPart = new Chapter(new Paragraph(anchor), 1);

		Paragraph subPara = new Paragraph("Dados do prestador", subFont);
		Section subCatPart = catPart.addSection(subPara);

		subCatPart.add(new Paragraph("CNPJ: " + prestador.getCnpj()));
		subCatPart.add(new Paragraph("Código CNES: " + prestador.getCodigoCnes()));
		subCatPart.add(new Paragraph("Nome Fantasia: " + prestador.getNomeFantasia()));
		subCatPart.add(new Paragraph("Nome Empresarial: " + prestador.getNomeEmpresarial()));
		subCatPart.add(new Paragraph("Observação: " + observacao));

		Paragraph paragraph = new Paragraph();
		addEmptyLine(paragraph, 2);
		subCatPart.add(paragraph);

		Paragraph subParaProcedimento = new Paragraph("Procedimentos", subFont);
		addEmptyLine(subParaProcedimento, 1);
		subCatPart = catPart.addSection(subParaProcedimento);
		createTable(subCatPart, prestador);

		document.add(catPart);

		document.close();

		// InputStream inputStream = new ByteArrayInputStream(baos.toByteArray());
		return baos.toByteArray();
	}

	private static void createTable(Section subCatPart, Prestador prestador) throws BadElementException {

		PdfPTable table = new PdfPTable(3);

		PdfPCell c1 = new PdfPCell(new Phrase("Procedimento"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Descrição"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Valor"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		prestador.getPrestadorProcedimentos().forEach(item -> {
			table.addCell(item.getProcedimento().getCodigoExtendido());
			table.addCell(item.getProcedimento().getDescricao());
			table.addCell(item.getValor().toPlainString());
		});

		subCatPart.add(table);

	}

	private static void addEmptyLine(Paragraph paragraph, int number) {

		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

}
