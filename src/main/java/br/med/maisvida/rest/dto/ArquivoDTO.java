package br.med.maisvida.rest.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CNPJ;

public class ArquivoDTO {

	@NotBlank
	private String arquivoBase64;

	@NotBlank
	@CNPJ
	private String cnpj;

	public ArquivoDTO() {

	}

	public String getArquivoBase64() {

		return arquivoBase64;
	}

	public void setArquivoBase64(String arquivoBase64) {

		this.arquivoBase64 = arquivoBase64;
	}

	public String getCnpj() {

		return cnpj;
	}

	public void setCnpj(String cnpj) {

		this.cnpj = cnpj;
	}
}
