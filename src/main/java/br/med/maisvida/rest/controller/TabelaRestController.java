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

import br.med.maisvida.entity.Tabela;
import br.med.maisvida.rest.dto.TabelaDTO;
import br.med.maisvida.service.TabelaService;

@RestController
@Validated
public class TabelaRestController {

	@Autowired
	private TabelaService service;

	@GetMapping(value = "/cbhpm/tabelas/codigo/{codigo}")
	public ResponseEntity<TabelaDTO> buscarPorCodigo(@NotNull @PathVariable(value = "codigo") Integer codigo) {

		Tabela tabela = service.buscarPorCodigo(codigo);
		TabelaDTO response = new TabelaDTO(tabela);
		return response == null ? ResponseEntity.notFound().build() : new ResponseEntity<TabelaDTO>(response, HttpStatus.OK);
	}

	@GetMapping(value = { "/cbhpm/tabelas/", "/cbhpm/tabelas" })
	public ResponseEntity<List<TabelaDTO>> buscarTodos() {

		final List<Tabela> tabelas = service.buscarTodos();
		final List<TabelaDTO> response = new ArrayList<>();
		tabelas.stream().forEach(item -> response.add(new TabelaDTO(item)));
		return response == null ? ResponseEntity.notFound().build() : new ResponseEntity<List<TabelaDTO>>(response, HttpStatus.OK);
	}
}
