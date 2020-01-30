package br.med.maisvida.rest.dto.prestador;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.br.CNPJ;

public class NotificarRejeicaoDTO {

	@NotBlank
	@CNPJ
	private String cnpj;

	@NotBlank
	private String motivo;

	/**
	 * Get the value for <code>cnpj</code>
	 *
	 * @return <code>String</code>
	 */
	public String getCnpj() {

		return cnpj;
	}

	/**
	 * Set the value for <code>cnpj</code>.
	 *
	 * @param cnpj
	 */
	public void setCnpj(String cnpj) {

		this.cnpj = cnpj;
	}

	/**
	 * Get the value for <code>motivo</code>
	 *
	 * @return <code>String</code>
	 */
	public String getMotivo() {

		return motivo;
	}

	/**
	 * Set the value for <code>motivo</code>.
	 *
	 * @param motivo
	 */
	public void setMotivo(String motivo) {

		this.motivo = motivo;
	}

	public boolean temParametrosPreenchidos() {

		return StringUtils.isNotBlank(this.cnpj) && StringUtils.isNotBlank(this.motivo);
	}

}
