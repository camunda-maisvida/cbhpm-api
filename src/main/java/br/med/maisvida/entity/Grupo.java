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

}
