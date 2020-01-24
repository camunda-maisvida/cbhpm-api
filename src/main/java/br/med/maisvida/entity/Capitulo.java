package br.med.maisvida.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "Capitulo")
@Table(name = "capitulos")
public class Capitulo extends EntidadeBase {

	/** Field serialVersionUID. */
	private static final long serialVersionUID = -5129454072767111204L;

	@NotNull
	@Column(name = "codigo", nullable = false, unique = true)
	private Integer codigo;

	@NotBlank
	@Size(max = 255)
	@Column(name = "descricao", nullable = false, length = 255)
	private String descricao;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "tabela_id", nullable = false)
	private Tabela tabela;

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
	 * Get the value for <code>tabela</code>
	 *
	 * @return <code>Tabela</code>
	 */
	public Tabela getTabela() {

		return tabela;
	}

	/**
	 * Set the value for <code>tabela</code>.
	 *
	 * @param tabela
	 */
	public void setTabela(Tabela tabela) {

		this.tabela = tabela;
	}

}
