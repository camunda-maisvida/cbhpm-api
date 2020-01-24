package br.med.maisvida.rest.dto.prestador;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonRootName;

import br.med.maisvida.entity.Procedimento;
import br.med.maisvida.entity.prestador.Prestador;

@JsonRootName(value = "prestador")
public class PrestadorResultDTO extends PrestadorDTO {

	private Map<Long, String> procedimentos = new HashMap<>();

	public PrestadorResultDTO() {

	}

	public PrestadorResultDTO( Prestador entidade ) {

		super(entidade);
		if (!CollectionUtils.isEmpty(entidade.getProcedimentos())) {
			this.procedimentos.putAll(entidade.getProcedimentos().stream().collect(Collectors.toMap(Procedimento::getId, Procedimento::getDescricao)));
		}
	}

	/**
	 * Get the value for <code>procedimentos</code>
	 *
	 * @return <code>Map<Long,String></code>
	 */
	public Map<Long, String> getProcedimentos() {

		return procedimentos;
	}

	/**
	 * Set the value for <code>procedimentos</code>.
	 *
	 * @param procedimentos
	 */
	public void setProcedimentos(Map<Long, String> procedimentos) {

		this.procedimentos = procedimentos;
	}

}
