package br.med.maisvida.rest.dto.prestador;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonRootName;

import br.med.maisvida.entity.prestador.Prestador;
import br.med.maisvida.rest.dto.EntidadeBaseDTO;

@JsonRootName(value = "prestador")
public class PrestadorProcedimentoDTO extends EntidadeBaseDTO<Prestador> {

	private Set<Long> procedimentosAdicionar = new HashSet<>();

	private Set<Long> procedimentosRemover = new HashSet<>();

	private Set<Long> procedimentosSobrepor = new HashSet<>();

	private Boolean removerTodos;

	public PrestadorProcedimentoDTO() {

	}

	/**
	 * Get the value for <code>procedimentosAdicionar</code>
	 *
	 * @return <code>Set<Long></code>
	 */
	public Set<Long> getProcedimentosAdicionar() {

		return procedimentosAdicionar;
	}

	/**
	 * Set the value for <code>procedimentosAdicionar</code>.
	 *
	 * @param procedimentosAdicionar
	 */
	public void setProcedimentosAdicionar(Set<Long> procedimentosAdicionar) {

		this.procedimentosAdicionar = procedimentosAdicionar;
	}

	/**
	 * Get the value for <code>procedimentosRemover</code>
	 *
	 * @return <code>Set<Long></code>
	 */
	public Set<Long> getProcedimentosRemover() {

		return procedimentosRemover;
	}

	/**
	 * Set the value for <code>procedimentosRemover</code>.
	 *
	 * @param procedimentosRemover
	 */
	public void setProcedimentosRemover(Set<Long> procedimentosRemover) {

		this.procedimentosRemover = procedimentosRemover;
	}

	/**
	 * Get the value for <code>procedimentosSobrepor</code>
	 *
	 * @return <code>Set<Long></code>
	 */
	public Set<Long> getProcedimentosSobrepor() {

		return procedimentosSobrepor;
	}

	/**
	 * Set the value for <code>procedimentosSobrepor</code>.
	 *
	 * @param procedimentosSobrepor
	 */
	public void setProcedimentosSobrepor(Set<Long> procedimentosSobrepor) {

		this.procedimentosSobrepor = procedimentosSobrepor;
	}

	/**
	 * Get the value for <code>removerTodos</code>
	 *
	 * @return <code>Boolean</code>
	 */
	public Boolean getRemoverTodos() {

		return removerTodos;
	}

	/**
	 * Set the value for <code>removerTodos</code>.
	 *
	 * @param removerTodos
	 */
	public void setRemoverTodos(Boolean removerTodos) {

		this.removerTodos = removerTodos;
	}

}
