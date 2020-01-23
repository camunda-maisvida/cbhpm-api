package br.med.maisvida.rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.med.maisvida.entity.Procedimento;
import br.med.maisvida.rest.dto.ParametroConsultaProcedimentoDTO;
import br.med.maisvida.rest.dto.ProcedimentoDTO;
import br.med.maisvida.service.ProcedimentoService;

@RestController
@Validated
public class ProcedimentoRestController {

	@Autowired
	private ProcedimentoService service;

	@GetMapping(value = "/cbhpm/procedimentos/codigoextendido/{codigo}")
	public ResponseEntity<ProcedimentoDTO> buscarPorCodigoExtendido(@NotNull @PathVariable(value = "codigo") String codigo) {

		Procedimento entidade = service.buscarPorCodigoExtendido(codigo);
		ProcedimentoDTO response = new ProcedimentoDTO(entidade);
		return response == null ? ResponseEntity.notFound().build() : new ResponseEntity<ProcedimentoDTO>(response, HttpStatus.OK);
	}

	@GetMapping(value = { "/cbhpm/procedimentos/", "/cbhpm/procedimentos" })
	public ResponseEntity<List<ProcedimentoDTO>> buscarTodos(@RequestParam(name = "page", required = false) Integer page, @RequestParam(name = "size", required = false) Integer size) {

		final List<Procedimento> entidades = service.buscarTodos(page, size);
		final List<ProcedimentoDTO> response = new ArrayList<>();
		entidades.stream().forEach(item -> response.add(new ProcedimentoDTO(item)));
		return response == null ? ResponseEntity.notFound().build() : new ResponseEntity<List<ProcedimentoDTO>>(response, HttpStatus.OK);
	}

	@PostMapping(value = { "/cbhpm/procedimentos/search/", "/cbhpm/procedimentos/search" })
	public ResponseEntity<List<ProcedimentoDTO>> buscarPorParametro(@RequestBody @NotNull ParametroConsultaProcedimentoDTO parametro) {

		final List<ProcedimentoDTO> response = service.buscarDTOPorParametro(parametro);
		return response == null ? ResponseEntity.notFound().build() : new ResponseEntity<List<ProcedimentoDTO>>(response, HttpStatus.OK);
	}
}
