package br.med.maisvida.rest.dto.prestador;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonRootName;

import br.med.maisvida.entity.prestador.Prestador;
import br.med.maisvida.rest.dto.EntidadeBaseDTO;

@JsonRootName(value = "prestador")
public class PrestadorProcedimentoDTO extends EntidadeBaseDTO<Prestador> {

	private Set<PrestadorProcedimentoItemDTO> procedimentosAdicionar = new HashSet<>();

	private Set<PrestadorProcedimentoItemDTO> procedimentosRemover = new HashSet<>();

	private Set<PrestadorProcedimentoItemDTO> procedimentosSobrepor = new HashSet<>();

	private Boolean removerTodos;

	public PrestadorProcedimentoDTO() {

	}

	/**
	 * Get the value for <code>procedimentosAdicionar</code>
	 *
	 * @return <code>Set<PrestadorProcedimentoItemDTO></code>
	 */
	public Set<PrestadorProcedimentoItemDTO> getProcedimentosAdicionar() {

		return procedimentosAdicionar;
	}

	/**
	 * Set the value for <code>procedimentosAdicionar</code>.
	 *
	 * @param procedimentosAdicionar
	 */
	public void setProcedimentosAdicionar(Set<PrestadorProcedimentoItemDTO> procedimentosAdicionar) {

		this.procedimentosAdicionar = procedimentosAdicionar;
	}

	/**
	 * Get the value for <code>procedimentosRemover</code>
	 *
	 * @return <code>Set<PrestadorProcedimentoItemDTO></code>
	 */
	public Set<PrestadorProcedimentoItemDTO> getProcedimentosRemover() {

		return procedimentosRemover;
	}

	/**
	 * Set the value for <code>procedimentosRemover</code>.
	 *
	 * @param procedimentosRemover
	 */
	public void setProcedimentosRemover(Set<PrestadorProcedimentoItemDTO> procedimentosRemover) {

		this.procedimentosRemover = procedimentosRemover;
	}

	/**
	 * Get the value for <code>procedimentosSobrepor</code>
	 *
	 * @return <code>Set<PrestadorProcedimentoItemDTO></code>
	 */
	public Set<PrestadorProcedimentoItemDTO> getProcedimentosSobrepor() {

		return procedimentosSobrepor;
	}

	/**
	 * Set the value for <code>procedimentosSobrepor</code>.
	 *
	 * @param procedimentosSobrepor
	 */
	public void setProcedimentosSobrepor(Set<PrestadorProcedimentoItemDTO> procedimentosSobrepor) {

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
