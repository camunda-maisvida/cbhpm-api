package br.med.maisvida.rest.dto.prestador;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "endereco")
public class EnderecoDTO {

	private String logradouro;

	private String numero;

	private String bairro;

	private String cidade;

	private String cep;

	private String uf;

	/**
	 * Get the value for <code>logradouro</code>
	 *
	 * @return <code>String</code>
	 */
	public String getLogradouro() {

		return logradouro;
	}

	/**
	 * Set the value for <code>logradouro</code>.
	 *
	 * @param logradouro
	 */
	public void setLogradouro(String logradouro) {

		this.logradouro = logradouro;
	}

	/**
	 * Get the value for <code>numero</code>
	 *
	 * @return <code>String</code>
	 */
	public String getNumero() {

		return numero;
	}

	/**
	 * Set the value for <code>numero</code>.
	 *
	 * @param numero
	 */
	public void setNumero(String numero) {

		this.numero = numero;
	}

	/**
	 * Get the value for <code>bairro</code>
	 *
	 * @return <code>String</code>
	 */
	public String getBairro() {

		return bairro;
	}

	/**
	 * Set the value for <code>bairro</code>.
	 *
	 * @param bairro
	 */
	public void setBairro(String bairro) {

		this.bairro = bairro;
	}

	/**
	 * Get the value for <code>cidade</code>
	 *
	 * @return <code>String</code>
	 */
	public String getCidade() {

		return cidade;
	}

	/**
	 * Set the value for <code>cidade</code>.
	 *
	 * @param cidade
	 */
	public void setCidade(String cidade) {

		this.cidade = cidade;
	}

	/**
	 * Get the value for <code>cep</code>
	 *
	 * @return <code>String</code>
	 */
	public String getCep() {

		return cep;
	}

	/**
	 * Set the value for <code>cep</code>.
	 *
	 * @param cep
	 */
	public void setCep(String cep) {

		this.cep = cep;
	}

	/**
	 * Get the value for <code>uf</code>
	 *
	 * @return <code>String</code>
	 */
	public String getUf() {

		return uf;
	}

	/**
	 * Set the value for <code>uf</code>.
	 *
	 * @param uf
	 */
	public void setUf(String uf) {

		this.uf = uf;
	}

	/**
	 * Default description: <br>
	 * <br>
	 *
	 * {@inheritDoc}
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "EnderecoDTO [" + ( logradouro != null ? "logradouro=" + logradouro + ", " : "" ) + ( numero != null ? "numero=" + numero + ", " : "" ) + ( bairro != null ? "bairro=" + bairro + ", " : "" ) + ( cidade != null ? "cidade=" + cidade + ", " : "" ) + ( cep != null ? "cep=" + cep + ", " : "" ) + ( uf != null ? "uf=" + uf : "" ) + "]";
	}

}
