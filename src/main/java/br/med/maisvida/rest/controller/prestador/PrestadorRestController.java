package br.med.maisvida.rest.controller.prestador;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.med.maisvida.entity.prestador.Prestador;
import br.med.maisvida.rest.dto.prestador.PrestadorDTO;
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
}
