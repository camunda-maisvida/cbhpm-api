package br.med.maisvida.rest.dto;

import javax.validation.constraints.NotBlank;

public class ArquivoDTO {

	@NotBlank
	private String arquivoBase64;

	public ArquivoDTO() {

	}

	public String getArquivoBase64() {

		return arquivoBase64;
	}

	public void setArquivoBase64(String arquivoBase64) {

		this.arquivoBase64 = arquivoBase64;
	}

}
