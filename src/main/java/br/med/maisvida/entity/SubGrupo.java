package br.med.maisvida.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "SubGrupo")
@Table(name = "subgrupos", schema = "public")
public class SubGrupo extends EntidadeCodDescBase {

	/** Field serialVersionUID. */
	private static final long serialVersionUID = 6499783328864076715L;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "grupo_id", nullable = false)
	private Grupo grupo;

	/**
	 * Get the value for <code>grupo</code>
	 *
	 * @return <code>Grupo</code>
	 */
	public Grupo getGrupo() {

		return grupo;
	}

	/**
	 * Set the value for <code>grupo</code>.
	 *
	 * @param grupo
	 */
	public void setGrupo(Grupo grupo) {

		this.grupo = grupo;
	}

}
