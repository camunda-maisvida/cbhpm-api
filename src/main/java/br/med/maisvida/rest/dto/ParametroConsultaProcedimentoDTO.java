package br.med.maisvida.rest.dto;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "parametroConsulta")
public class ParametroConsultaProcedimentoDTO {

	private Long tabelaId;

	private Long capituloId;

	private Long grupoId;

	private Long subGrupoId;

	private Long procedimentoId;

	private String procedimentoDescricao;

	/**
	 * Get the value for <code>tabelaId</code>
	 *
	 * @return <code>Long</code>
	 */
	public Long getTabelaId() {

		return tabelaId;
	}

	/**
	 * Set the value for <code>tabelaId</code>.
	 *
	 * @param tabelaId
	 */
	public void setTabelaId(Long tabelaId) {

		this.tabelaId = tabelaId;
	}

	/**
	 * Get the value for <code>capituloId</code>
	 *
	 * @return <code>Long</code>
	 */
	public Long getCapituloId() {

		return capituloId;
	}

	/**
	 * Set the value for <code>capituloId</code>.
	 *
	 * @param capituloId
	 */
	public void setCapituloId(Long capituloId) {

		this.capituloId = capituloId;
	}

	/**
	 * Get the value for <code>grupoId</code>
	 *
	 * @return <code>Long</code>
	 */
	public Long getGrupoId() {

		return grupoId;
	}

	/**
	 * Set the value for <code>grupoId</code>.
	 *
	 * @param grupoId
	 */
	public void setGrupoId(Long grupoId) {

		this.grupoId = grupoId;
	}

	/**
	 * Get the value for <code>subGrupoId</code>
	 *
	 * @return <code>Long</code>
	 */
	public Long getSubGrupoId() {

		return subGrupoId;
	}

	/**
	 * Set the value for <code>subGrupoId</code>.
	 *
	 * @param subGrupoId
	 */
	public void setSubGrupoId(Long subGrupoId) {

		this.subGrupoId = subGrupoId;
	}

	/**
	 * Get the value for <code>procedimentoId</code>
	 *
	 * @return <code>Long</code>
	 */
	public Long getProcedimentoId() {

		return procedimentoId;
	}

	/**
	 * Set the value for <code>procedimentoId</code>.
	 *
	 * @param procedimentoId
	 */
	public void setProcedimentoId(Long procedimentoId) {

		this.procedimentoId = procedimentoId;
	}

	/**
	 * Get the value for <code>procedimentoDescricao</code>
	 *
	 * @return <code>String</code>
	 */
	public String getProcedimentoDescricao() {

		return procedimentoDescricao;
	}

	/**
	 * Set the value for <code>procedimentoDescricao</code>.
	 *
	 * @param procedimentoDescricao
	 */
	public void setProcedimentoDescricao(String procedimentoDescricao) {

		this.procedimentoDescricao = procedimentoDescricao;
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

		return "ParametroConsultaProcedimentoDTO [" + ( tabelaId != null ? "tabelaId=" + tabelaId + ", " : "" ) + ( capituloId != null ? "capituloId=" + capituloId + ", " : "" ) + ( grupoId != null ? "grupoId=" + grupoId + ", " : "" ) + ( subGrupoId != null ? "subGrupoId=" + subGrupoId + ", " : "" ) + ( procedimentoId != null ? "procedimentoId=" + procedimentoId + ", " : "" ) + ( procedimentoDescricao != null ? "procedimentoDescricao=" + procedimentoDescricao : "" ) + "]";
	}

}
