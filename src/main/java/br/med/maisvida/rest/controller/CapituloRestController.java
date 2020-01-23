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
import org.springframework.web.bind.annotation.RestController;

import br.med.maisvida.entity.Capitulo;
import br.med.maisvida.rest.dto.CapituloDTO;
import br.med.maisvida.service.CapituloService;

@RestController
@Validated
public class CapituloRestController {

	@Autowired
	private CapituloService service;

	@GetMapping(value = "/cbhpm/capitulos/codigo/{codigo}")
	public ResponseEntity<CapituloDTO> buscarPorCodigo(@NotNull @PathVariable(value = "codigo") Integer codigo) {

		Capitulo entidade = service.buscarPorCodigo(codigo);
		CapituloDTO response = new CapituloDTO(entidade);
		return response == null ? ResponseEntity.notFound().build() : new ResponseEntity<CapituloDTO>(response, HttpStatus.OK);
	}

	@GetMapping(value = { "/cbhpm/capitulos/", "/cbhpm/capitulos" })
	public ResponseEntity<List<CapituloDTO>> buscarTodos() {

		final List<Capitulo> entidades = service.buscarTodos();
		final List<CapituloDTO> response = new ArrayList<>();
		entidades.stream().forEach(item -> response.add(new CapituloDTO(item)));
		return response == null ? ResponseEntity.notFound().build() : new ResponseEntity<List<CapituloDTO>>(response, HttpStatus.OK);
	}

	@GetMapping(value = { "/cbhpm/capitulos/tabela/{idTabela}" })
	public ResponseEntity<List<CapituloDTO>> buscarPorTabela(@NotNull @PathVariable(value = "idTabela") Long idTabela) {

		final List<Capitulo> entidades = service.buscarPorTabela(idTabela);
		final List<CapituloDTO> response = new ArrayList<>();
		entidades.stream().forEach(item -> response.add(new CapituloDTO(item)));
		return response == null ? ResponseEntity.notFound().build() : new ResponseEntity<List<CapituloDTO>>(response, HttpStatus.OK);
	}
}
