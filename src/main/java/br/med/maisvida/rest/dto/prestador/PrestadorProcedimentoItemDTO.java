package br.med.maisvida.rest.dto.prestador;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "procedimentoItem")
public class PrestadorProcedimentoItemDTO {

	@NotNull
	@Positive
	private Long id;

	@NotNull
	@Positive
	private BigDecimal valor;

	/**
	 * Get the value for <code>id</code>
	 *
	 * @return <code>Long</code>
	 */
	public Long getId() {

		return id;
	}

	/**
	 * Set the value for <code>id</code>.
	 *
	 * @param id
	 */
	public void setId(Long id) {

		this.id = id;
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
	 * Default description: <br>
	 * <br>
	 *
	 * {@inheritDoc}
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "PrestadorProcedimentoItemDTO [" + ( id != null ? "id=" + id + ", " : "" ) + ( valor != null ? "valor=" + valor : "" ) + "]";
	}

}
