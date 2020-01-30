package br.med.maisvida.rest.dto.prestador;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import br.med.maisvida.entity.prestador.Prestador;
import br.med.maisvida.rest.dto.ProcedimentoDTO;

@JsonRootName(value = "prestador")
public class PrestadorFullResultDTO extends PrestadorDTO {

	@JsonProperty("prestadorProcedimentoItem")
	private Set<PrestadorProcedimentoItemDTO> procedimentos = new HashSet<>();

	public PrestadorFullResultDTO() {

	}

	public PrestadorFullResultDTO( Prestador entidade ) {

		super(entidade);

		if (!CollectionUtils.isEmpty(entidade.getPrestadorProcedimentos())) {

			entidade.getPrestadorProcedimentos().stream().forEach(item -> {
				PrestadorProcedimentoItemDTO prestadorProcedimento = new PrestadorProcedimentoItemDTO();
				BeanUtils.copyProperties(item, prestadorProcedimento);
				ProcedimentoDTO procedimento = new ProcedimentoDTO(item.getProcedimento());
				prestadorProcedimento.setProcedimento(procedimento);
				this.getProcedimentos().add(prestadorProcedimento);
			});
		}

	}

	public Set<PrestadorProcedimentoItemDTO> getProcedimentos() {

		return procedimentos;
	}

	public void setProcedimentos(Set<PrestadorProcedimentoItemDTO> procedimentos) {

		this.procedimentos = procedimentos;
	}

}
