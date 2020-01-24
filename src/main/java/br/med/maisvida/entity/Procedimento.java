package br.med.maisvida.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity(name = "Procedimento")
@Table(name = "procedimentos", schema = "public")
public class Procedimento extends EntidadeCodDescBase {

	/** Field serialVersionUID. */
	private static final long serialVersionUID = 2871019017387535227L;

	@NotBlank
	@Column(name = "porte", nullable = false)
	private String porte;

	@NotNull
	@Column(name = "uco", nullable = false, precision = 9, scale = 3)
	private BigDecimal uco;

	@NotNull
	@Column(name = "num_auxiliares", nullable = false)
	private Integer numAuxiliares = 0;

	@NotNull
	@Column(name = "porte_anest", nullable = false)
	private Integer porteAnest = 0;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "subgrupo_id", nullable = false)
	private SubGrupo subGrupo;

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
	 * Get the value for <code>subGrupo</code>
	 *
	 * @return <code>SubGrupo</code>
	 */
	public SubGrupo getSubGrupo() {

		return subGrupo;
	}

	/**
	 * Set the value for <code>subGrupo</code>.
	 *
	 * @param subGrupo
	 */
	public void setSubGrupo(SubGrupo subGrupo) {

		this.subGrupo = subGrupo;
	}

}
