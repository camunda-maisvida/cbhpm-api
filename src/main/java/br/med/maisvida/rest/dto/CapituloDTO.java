package br.med.maisvida.rest.dto;

import com.fasterxml.jackson.annotation.JsonRootName;

import br.med.maisvida.entity.Capitulo;

@JsonRootName(value = "capitulo")
public class CapituloDTO extends EntidadeBaseDTO<Capitulo> {

	private Integer codigo;

	private String descricao;

	public CapituloDTO( Capitulo entidade ) {

		super(entidade);
	}

	/**
	 * Get the value for <code>codigo</code>
	 *
	 * @return <code>Integer</code>
	 */
	public Integer getCodigo() {

		return codigo;
	}

	/**
	 * Set the value for <code>codigo</code>.
	 *
	 * @param codigo
	 */
	public void setCodigo(Integer codigo) {

		this.codigo = codigo;
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "CapituloDTO [" + ( codigo != null ? "codigo=" + codigo + ", " : "" ) + ( descricao != null ? "descricao=" + descricao : "" ) + "]";
	}

}
