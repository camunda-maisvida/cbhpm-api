package br.med.maisvida.rest.controller.email;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.med.maisvida.rest.dto.email.EmailDTO;
import br.med.maisvida.service.email.EnviadorDeEmailService;

@CrossOrigin
@RestController
public class EnviadorDeEmailRestController {

	@Autowired
	private EnviadorDeEmailService service;

	@PostMapping(value = "/email/enviar")
	public ResponseEntity<Object> enviar(@RequestBody(required = true) @Valid EmailDTO mail) {

		service.enviar(mail);

		return new ResponseEntity<>("O email está sendo enviado!", HttpStatus.ACCEPTED);
	}
}
