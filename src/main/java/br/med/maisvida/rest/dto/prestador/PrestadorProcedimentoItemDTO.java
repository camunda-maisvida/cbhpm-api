package br.med.maisvida.rest.dto.prestador;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;

import br.med.maisvida.rest.dto.ProcedimentoDTO;

@JsonRootName(value = "procedimentoItem")
public class PrestadorProcedimentoItemDTO {

	/** Field id. */
	@NotNull
	@Positive
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Long idProcedimento;

	@NotNull
	@Positive
	private BigDecimal valor;

	@Positive
	private BigDecimal valorProposto;

	private ProcedimentoDTO procedimento;

	/**
	 * Get the value for <code>idProcedimento</code>
	 *
	 * @return <code>Long</code>
	 */
	public Long getIdProcedimento() {

		return idProcedimento;
	}

	/**
	 * Set the value for <code>idProcedimento</code>.
	 *
	 * @param id
	 */
	public void setIdProcedimento(Long idProcedimento) {

		this.idProcedimento = idProcedimento;
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
	 * Get the value for <code>procedimento</code>
	 *
	 * @return <code>ProcedimentoDTO</code>
	 */
	public ProcedimentoDTO getProcedimento() {

		return procedimento;
	}

	/**
	 * Set the value for <code>procedimento</code>.
	 *
	 * @param procedimento
	 */
	public void setProcedimento(ProcedimentoDTO procedimento) {

		this.procedimento = procedimento;
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

		return "PrestadorProcedimentoItemDTO [" + ( idProcedimento != null ? "idProcedimento=" + idProcedimento + ", " : "" ) + ( valor != null ? "valor=" + valor + ", " : "" ) + ( valorProposto != null ? "valorProposto=" + valorProposto + ", " : "" ) + ( procedimento != null ? "procedimento=" + procedimento : "" ) + "]";
	}

}
