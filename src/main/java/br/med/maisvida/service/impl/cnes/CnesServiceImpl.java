package br.med.maisvida.service.impl.cnes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.med.maisvida.service.cnes.CnesService;

@Service
public class CnesServiceImpl implements CnesService {

	private static final String URL_CNES_API = "https://cnes-api.herokuapp.com/cnes/estabelecimento/cnpj/%s";

	// private static final String URL_LOCAL_CNES_API = "http://localhost:8880/cnes/estabelecimento/cnpj/%s";

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public String buscarPorCnpj(String cnpj) {

		String url = String.format(URL_CNES_API, cnpj);
		String result = restTemplate.getForObject(url, String.class);
		return result;
	}
}
