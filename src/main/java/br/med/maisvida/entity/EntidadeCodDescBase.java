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

	/**
	 * Default description: <br>
	 * <br>
	 *
	 * {@inheritDoc}
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( codigo == null ) ? 0 : codigo.hashCode() );
		result = prime * result + ( ( codigoExtendido == null ) ? 0 : codigoExtendido.hashCode() );
		result = prime * result + ( ( descricao == null ) ? 0 : descricao.hashCode() );
		return result;
	}

	/**
	 * Default description: <br>
	 * <br>
	 *
	 * {@inheritDoc}
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntidadeCodDescBase other = (EntidadeCodDescBase) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (codigoExtendido == null) {
			if (other.codigoExtendido != null)
				return false;
		} else if (!codigoExtendido.equals(other.codigoExtendido))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}

}
