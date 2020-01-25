package br.med.maisvida.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Grupo")
@Table(name = "grupos", schema = "public")
public class Grupo extends EntidadeCodDescBase {

	/** Field serialVersionUID. */
	private static final long serialVersionUID = -940448222229945483L;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "capitulo_id", nullable = false)
	private Capitulo capitulo;

	/**
	 * Get the value for <code>capitulo</code>
	 *
	 * @return <code>Capitulo</code>
	 */
	public Capitulo getCapitulo() {

		return capitulo;
	}

	/**
	 * Set the value for <code>capitulo</code>.
	 *
	 * @param capitulo
	 */
	public void setCapitulo(Capitulo capitulo) {

		this.capitulo = capitulo;
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
		result = prime * result + ( ( capitulo == null ) ? 0 : capitulo.hashCode() );
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
		Grupo other = (Grupo) obj;
		if (capitulo == null) {
			if (other.capitulo != null)
				return false;
		} else if (!capitulo.equals(other.capitulo))
			return false;
		return true;
	}

}
