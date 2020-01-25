package br.med.maisvida.entity.prestador;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.med.maisvida.entity.Procedimento;

@Embeddable
public class PrestadorProcedimentoPK implements Serializable {

	/** Field serialVersionUID. */
	private static final long serialVersionUID = 6919697433557885527L;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "prestador_id", nullable = false)
	private Prestador prestadorId;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "procedimento_id", nullable = false)
	private Procedimento procedimentoId;

	public PrestadorProcedimentoPK() {

	}

	public PrestadorProcedimentoPK( Prestador prestadorId, Procedimento procedimentoId ) {

		this.prestadorId = prestadorId;
		this.procedimentoId = procedimentoId;

	}

	/**
	 * Get the value for <code>prestadorId</code>
	 *
	 * @return <code>Prestador</code>
	 */
	public Prestador getPrestadorId() {

		return prestadorId;
	}

	/**
	 * Set the value for <code>prestadorId</code>.
	 *
	 * @param prestadorId
	 */
	public void setPrestadorId(Prestador prestadorId) {

		this.prestadorId = prestadorId;
	}

	/**
	 * Get the value for <code>procedimentoId</code>
	 *
	 * @return <code>Procedimento</code>
	 */
	public Procedimento getProcedimentoId() {

		return procedimentoId;
	}

	/**
	 * Set the value for <code>procedimentoId</code>.
	 *
	 * @param procedimentoId
	 */
	public void setProcedimentoId(Procedimento procedimentoId) {

		this.procedimentoId = procedimentoId;
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
		result = prime * result + ( ( prestadorId == null ) ? 0 : prestadorId.hashCode() );
		result = prime * result + ( ( procedimentoId == null ) ? 0 : procedimentoId.hashCode() );
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
		PrestadorProcedimentoPK other = (PrestadorProcedimentoPK) obj;
		if (prestadorId == null) {
			if (other.prestadorId != null)
				return false;
		} else if (!prestadorId.equals(other.prestadorId))
			return false;
		if (procedimentoId == null) {
			if (other.procedimentoId != null)
				return false;
		} else if (!procedimentoId.equals(other.procedimentoId))
			return false;
		return true;
	}

	/**
	 * Default description: <br> <br>
	 *
	 * {@inheritDoc}
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "PrestadorProcedimentoPK [" + ( prestadorId != null ? "prestadorId=" + prestadorId.getId() + ", " : "" ) + ( procedimentoId != null ? "procedimentoId=" + procedimentoId.getId() : "" ) + "]";
	}
	
	

}
