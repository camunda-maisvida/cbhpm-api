package br.med.maisvida.rest.dto;

import br.med.maisvida.entity.EntidadeCodDescBase;

public abstract class EntidadeCodDescDTOBase<T extends EntidadeCodDescBase> extends EntidadeBaseDTO<T> {

	private String codigo;

	private String codigoExtendido;

	private String descricao;

	
	public EntidadeCodDescDTOBase() {

	}

	public EntidadeCodDescDTOBase( T entidade ) {

		super.populate(entidade);
	}
	
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
