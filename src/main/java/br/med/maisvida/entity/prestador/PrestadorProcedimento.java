package br.med.maisvida.entity.prestador;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.med.maisvida.entity.Procedimento;

@Entity(name = "PrestadorProcedimento")
@Table(name = "prestador_procedimentos", schema = "prestador")
public class PrestadorProcedimento {

	@EmbeddedId
	private PrestadorProcedimentoPK id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("prestadorId")
	private Prestador prestador;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("procedimentoId")
	private Procedimento procedimento;

	@NotNull
	@Positive
	@Column(name = "valor", nullable = false, precision = 9, scale = 2)
	private BigDecimal valor;

	@Positive
	@Column(name = "valor_proposto", precision = 9, scale = 2)
	private BigDecimal valorProposto;

	public PrestadorProcedimento() {

	}

	public PrestadorProcedimento( Prestador prestador, Procedimento procedimento, BigDecimal valor, BigDecimal valorProposto ) {

		this.prestador = prestador;
		this.procedimento = procedimento;
		this.valor = valor;
		this.valorProposto = valorProposto;
		this.id = new PrestadorProcedimentoPK(prestador, procedimento);
	}

	/**
	 * Get the value for <code>id</code>
	 *
	 * @return <code>PrestadorProcedimentoPK</code>
	 */
	public PrestadorProcedimentoPK getId() {

		return id;
	}

	/**
	 * Set the value for <code>id</code>.
	 *
	 * @param id
	 */
	public void setId(PrestadorProcedimentoPK id) {

		this.id = id;
	}

	/**
	 * Get the value for <code>prestador</code>
	 *
	 * @return <code>Prestador</code>
	 */
	public Prestador getPrestador() {

		return prestador;
	}

	/**
	 * Set the value for <code>prestador</code>.
	 *
	 * @param prestador
	 */
	public void setPrestador(Prestador prestador) {

		this.prestador = prestador;
	}

	/**
	 * Get the value for <code>procedimento</code>
	 *
	 * @return <code>Procedimento</code>
	 */
	public Procedimento getProcedimento() {

		return procedimento;
	}

	/**
	 * Set the value for <code>procedimento</code>.
	 *
	 * @param procedimento
	 */
	public void setProcedimento(Procedimento procedimento) {

		this.procedimento = procedimento;
	}

	/**
	 * Get the value for <code>valor</code>
	 *
	 * @return <code>BigDecimal</code>
	 */
	public BigDecimal getValor() {

		return valor;
	}

	/**
	 * Set the value for <code>valor</code>.
	 *
	 * @param valor
	 */
	public void setValor(BigDecimal valor) {

		this.valor = valor;
	}

	/**
	 * Get the value for <code>valorProposto</code>
	 *
	 * @return <code>BigDecimal</code>
	 */
	public BigDecimal getValorProposto() {

		return valorProposto;
	}

	/**
	 * Set the value for <code>valorProposto</code>.
	 *
	 * @param valorProposto
	 */
	public void setValorProposto(BigDecimal valorProposto) {

		this.valorProposto = valorProposto;
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
		int result = 1;
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrestadorProcedimento other = (PrestadorProcedimento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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

		return "PrestadorProcedimento [" + ( id != null ? "id=" + id : "" ) + "]";
	}

}
