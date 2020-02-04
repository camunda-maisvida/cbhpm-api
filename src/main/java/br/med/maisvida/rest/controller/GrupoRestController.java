package br.med.maisvida.rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.med.maisvida.entity.Grupo;
import br.med.maisvida.rest.dto.GrupoDTO;
import br.med.maisvida.service.GrupoService;

@CrossOrigin
@RestController
@Validated
public class GrupoRestController {

	@Autowired
	private GrupoService service;

	@GetMapping(value = "/cbhpm/grupos/codigoextendido/{codigo}")
	public ResponseEntity<GrupoDTO> buscarPorCodigoExtendido(@NotNull @PathVariable(value = "codigo") String codigo) {

		Grupo entidade = service.buscarPorCodigoExtendido(codigo);
		GrupoDTO response = new GrupoDTO(entidade);
		return response == null ? ResponseEntity.notFound().build() : new ResponseEntity<GrupoDTO>(response, HttpStatus.OK);
	}

	@GetMapping(value = { "/cbhpm/grupos/", "/cbhpm/grupos" })
	public ResponseEntity<List<GrupoDTO>> buscarTodos() {

		final List<Grupo> entidades = service.buscarTodos();
		final List<GrupoDTO> response = new ArrayList<>();
		entidades.stream().forEach(item -> response.add(new GrupoDTO(item)));
		return response == null ? ResponseEntity.notFound().build() : new ResponseEntity<List<GrupoDTO>>(response, HttpStatus.OK);
	}

	@GetMapping(value = { "/cbhpm/grupos/capitulo/{idCapitulo}" })
	public ResponseEntity<List<GrupoDTO>> buscarPorCapitulo(@NotNull @PathVariable(value = "idCapitulo") Long idCapitulo) {

		final List<Grupo> entidades = service.buscarPorCapitulo(idCapitulo);
		final List<GrupoDTO> response = new ArrayList<>();
		entidades.stream().forEach(item -> response.add(new GrupoDTO(item)));
		return response == null ? ResponseEntity.notFound().build() : new ResponseEntity<List<GrupoDTO>>(response, HttpStatus.OK);
	}
}
