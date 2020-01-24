package br.med.maisvida.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class EntidadeCodDescBase extends EntidadeBase {

	/** Field serialVersionUID. */
	private static final long serialVersionUID = 3479719450429003952L;

	@NotBlank
	@Size(max = 10)
	@Column(name = "codigo", nullable = false, length = 10)
	private String codigo;

	@NotBlank
	@Size(max = 50)
	@Column(name = "codigo_extendido", nullable = false, length = 50)
	private String codigoExtendido;

	@NotBlank
	@Size(max = 255)
	@Column(name = "descricao", nullable = false, length = 255)
	private String descricao;

	/**
	 * Get the value for <code>codigo</code>
	 *
	 * @return <code>String</code>
	 */
	public String getCodigo() {

		return codigo;
	}

	/**
	 * Set the value for <code>codigo</code>.
	 *
	 * @param codigo
	 */
	public void setCodigo(String codigo) {

		this.codigo = codigo;
	}

	/**
	 * Get the value for <code>codigoExtendido</code>
	 *
	 * @return <code>String</code>
	 */
	public String getCodigoExtendido() {

		return codigoExtendido;
	}

	/**
	 * Set the value for <code>codigoExtendido</code>.
	 *
	 * @param codigoExtendido
	 */
	public void setCodigoExtendido(String codigoExtendido) {

		this.codigoExtendido = codigoExtendido;
	}

	/**
	 * Get the value for <code>descricao</code>
	 *
	 * @return <code>String</code>
	 */
	public String getDescricao() {

		return descricao;
	}

	/**
	 * Set the value for <code>descricao</code>.
	 *
	 * @param descricao
	 */
	public void setDescricao(String descricao) {

		this.descricao = descricao;
	}

}
