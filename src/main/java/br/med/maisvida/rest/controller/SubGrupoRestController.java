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

import br.med.maisvida.entity.SubGrupo;
import br.med.maisvida.rest.dto.SubGrupoDTO;
import br.med.maisvida.service.SubGrupoService;

@RestController
@Validated
public class SubGrupoRestController {

	@Autowired
	private SubGrupoService service;

	@GetMapping(value = "/cbhpm/subgrupos/codigoextendido/{codigo}")
	public ResponseEntity<SubGrupoDTO> buscarPorCodigoExtendido(@NotNull @PathVariable(value = "codigo") String codigo) {

		SubGrupo entidade = service.buscarPorCodigoExtendido(codigo);
		SubGrupoDTO response = new SubGrupoDTO(entidade);
		return response == null ? ResponseEntity.notFound().build() : new ResponseEntity<SubGrupoDTO>(response, HttpStatus.OK);
	}

	@GetMapping(value = { "/cbhpm/subgrupos/", "/cbhpm/subgrupos" })
	public ResponseEntity<List<SubGrupoDTO>> buscarTodos() {

		final List<SubGrupo> entidades = service.buscarTodos();
		final List<SubGrupoDTO> response = new ArrayList<>();
		entidades.stream().forEach(item -> response.add(new SubGrupoDTO(item)));
		return response == null ? ResponseEntity.notFound().build() : new ResponseEntity<List<SubGrupoDTO>>(response, HttpStatus.OK);
	}

	@GetMapping(value = { "/cbhpm/subgrupos/grupo/{idGrupo}" })
	public ResponseEntity<List<SubGrupoDTO>> buscarPorCapitulo(@NotNull @PathVariable(value = "idGrupo") Long idGrupo) {

		final List<SubGrupo> entidades = service.buscarPorGrupo(idGrupo);
		final List<SubGrupoDTO> response = new ArrayList<>();
		entidades.stream().forEach(item -> response.add(new SubGrupoDTO(item)));
		return response == null ? ResponseEntity.notFound().build() : new ResponseEntity<List<SubGrupoDTO>>(response, HttpStatus.OK);
	}
}
