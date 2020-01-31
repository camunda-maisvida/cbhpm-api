package br.med.maisvida.rest.controller.prestador;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;

import br.med.maisvida.entity.prestador.Prestador;
import br.med.maisvida.rest.dto.prestador.CnpjComMotivoDTO;
import br.med.maisvida.rest.dto.prestador.PrestadorDTO;
import br.med.maisvida.rest.dto.prestador.PrestadorProcedimentoDTO;
import br.med.maisvida.rest.dto.prestador.PrestadorResultDTO;
import br.med.maisvida.service.prestador.PrestadorService;

@RestController
@Validated
public class PrestadorRestController {

	@Autowired
	private PrestadorService service;

	@PostMapping(value = { "/prestadores/", "/prestadores" })
	public ResponseEntity<PrestadorDTO> buscarPorParametro(@Valid @RequestBody @NotNull PrestadorDTO parametro) {

		final PrestadorDTO response = service.salvarRetornandoDTO(parametro);

		return response == null ? ResponseEntity.notFound().build() : new ResponseEntity<PrestadorDTO>(response, HttpStatus.OK);
	}

	@GetMapping(value = { "/prestadores/", "/prestadores" })
	public ResponseEntity<List<PrestadorDTO>> buscarTodos(@RequestParam(name = "page", required = false) Integer page, @RequestParam(name = "size", required = false) Integer size) {

		final List<Prestador> entidades = service.buscarTodos(page, size);
		final List<PrestadorDTO> response = new ArrayList<>();
		entidades.stream().forEach(item -> response.add(new PrestadorDTO(item)));
		return response == null ? ResponseEntity.notFound().build() : new ResponseEntity<List<PrestadorDTO>>(response, HttpStatus.OK);
	}

	@GetMapping("/prestadores/cnpj/{cnpj}")
	public ResponseEntity<PrestadorDTO> buscarPorCnpj(@PathVariable(value = "cnpj") @NotNull @CNPJ String cnpj) {

		final PrestadorDTO response = service.buscarDTOPorCnpj(cnpj);
		return response == null ? ResponseEntity.notFound().build() : new ResponseEntity<PrestadorDTO>(response, HttpStatus.OK);
	}

	@PostMapping(value = { "/prestadores/procedimentos/", "/prestadores/procedimentos" })
	public ResponseEntity<PrestadorResultDTO> atualizarProcedimentos(@Valid @RequestBody @NotNull PrestadorProcedimentoDTO parametro) {

		final PrestadorResultDTO response = service.atualizarProcedimentos(parametro);

		return response == null ? ResponseEntity.notFound().build() : new ResponseEntity<PrestadorResultDTO>(response, HttpStatus.OK);
	}

	@PostMapping(value = { "/prestadores/rejeitar/", "/prestadores/rejeitar" })
	public ResponseEntity<?> rejetiar(@Valid @RequestBody @NotNull CnpjComMotivoDTO rejeicao) {

		boolean ok = service.notificarRejeicao(rejeicao);

		return ok ? ResponseEntity.ok().build() : ResponseEntity.badRequest().body("Não foi possível realizar esta operação. Contato o suporte!");
	}

	@PostMapping(value = "/prestadores/contrato", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> gerarContrato(@Valid @RequestBody @NotNull CnpjComMotivoDTO parametro) throws FileNotFoundException, DocumentException {

		InputStream inputStream = this.service.gerarContrato(parametro.getCnpj(), parametro.getMotivo());
		ByteArrayInputStream bis = (ByteArrayInputStream) inputStream;

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=CONTRATO_PRESTADOR_" + parametro.getCnpj() + ".pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bis));
	}

}
