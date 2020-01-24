package br.med.maisvida.rest.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonRootName;

import br.med.maisvida.entity.Procedimento;

@JsonRootName(value = "procedimento")
public class ProcedimentoDTO extends EntidadeCodDescDTOBase<Procedimento> {

	private Long tabelaId;

	private Long capituloId;

	private Long grupoId;

	private Long subGrupoId;

	private String porte;

	private BigDecimal uco;

	private Integer numAuxiliares;

	private Integer porteAnest;
	
	public ProcedimentoDTO( Procedimento entidade ) {
		super(entidade);
		this.subGrupoId = entidade.getSubGrupo().getId();
		this.grupoId = entidade.getSubGrupo().getGrupo().getId();
		this.capituloId = entidade.getSubGrupo().getGrupo().getCapitulo().getId();
		this.tabelaId = entidade.getSubGrupo().getGrupo().getCapitulo().getTabela().getId();
	}

	public ProcedimentoDTO( Long id, String codigo, String codigoExtendido, String descricao, String porte, BigDecimal uco, Integer numAuxiliares, Integer porteAnest, Long tabelaId, Long capituloId, Long grupoId, Long subGrupoId ) {
		super();
		super.setCodigo(codigo);
		super.setCodigoExtendido(codigoExtendido);
		super.setDescricao(descricao);
		super.setId(id);
		this.tabelaId = tabelaId;
		this.capituloId = capituloId;
		this.grupoId = grupoId;
		this.subGrupoId = subGrupoId;
		this.porte = porte;
		this.uco = uco;
		this.numAuxiliares = numAuxiliares;
		this.porteAnest = porteAnest;
	}

	/**
	 * Get the value for <code>porte</code>
	 *
	 * @return <code>String</code>
	 */
	public String getPorte() {

		return porte;
	}

	/**
	 * Set the value for <code>porte</code>.
	 *
	 * @param porte
	 */
	public void setPorte(String porte) {

		this.porte = porte;
	}

	/**
	 * Get the value for <code>uco</code>
	 *
	 * @return <code>BigDecimal</code>
	 */
	public BigDecimal getUco() {

		return uco;
	}

	/**
	 * Set the value for <code>uco</code>.
	 *
	 * @param uco
	 */
	public void setUco(BigDecimal uco) {

		this.uco = uco;
	}

	/**
	 * Get the value for <code>numAuxiliares</code>
	 *
	 * @return <code>Integer</code>
	 */
	public Integer getNumAuxiliares() {

		return numAuxiliares;
	}

	/**
	 * Set the value for <code>numAuxiliares</code>.
	 *
	 * @param numAuxiliares
	 */
	public void setNumAuxiliares(Integer numAuxiliares) {

		this.numAuxiliares = numAuxiliares;
	}

	/**
	 * Get the value for <code>porteAnest</code>
	 *
	 * @return <code>Integer</code>
	 */
	public Integer getPorteAnest() {

		return porteAnest;
	}

	/**
	 * Set the value for <code>porteAnest</code>.
	 *
	 * @param porteAnest
	 */
	public void setPorteAnest(Integer porteAnest) {

		this.porteAnest = porteAnest;
	}

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
	 * Default description: <br>
	 * <br>
	 *
	 * {@inheritDoc}
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "ProcedimentoDTO [" + ( tabelaId != null ? "tabelaId=" + tabelaId + ", " : "" ) + ( capituloId != null ? "capituloId=" + capituloId + ", " : "" ) + ( grupoId != null ? "grupoId=" + grupoId + ", " : "" ) + ( subGrupoId != null ? "subGrupoId=" + subGrupoId + ", " : "" ) + ( porte != null ? "porte=" + porte + ", " : "" ) + ( uco != null ? "uco=" + uco + ", " : "" ) + ( numAuxiliares != null ? "numAuxiliares=" + numAuxiliares + ", " : "" )
				+ ( porteAnest != null ? "porteAnest=" + porteAnest + ", " : "" ) + ( getCodigo() != null ? "getCodigo()=" + getCodigo() + ", " : "" ) + ( getCodigoExtendido() != null ? "getCodigoExtendido()=" + getCodigoExtendido() + ", " : "" ) + ( getDescricao() != null ? "getDescricao()=" + getDescricao() + ", " : "" ) + ( getId() != null ? "getId()=" + getId() : "" ) + "]";
	}

}
