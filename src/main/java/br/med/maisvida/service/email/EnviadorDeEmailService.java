package br.med.maisvida.service.email;

import br.med.maisvida.rest.dto.email.EmailDTO;

public interface EnviadorDeEmailService {

	public void enviar(final EmailDTO mail);

}
