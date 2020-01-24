package br.med.maisvida.entity.prestador;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Endereco {

	@Column(name = "end_logradouro")
	private String logradouro;

	@Column(name = "end_numero")
	private String numero;

	@Column(name = "end_bairro")
	private String bairro;

	@Column(name = "end_cidade")
	private String cidade;

	@Column(name = "end_cep")
	private String cep;

	@Column(name = "end_uf")
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

}
