package br.med.maisvida.rest.dto;

import com.fasterxml.jackson.annotation.JsonRootName;

import br.med.maisvida.entity.SubGrupo;

@JsonRootName(value = "subgrupo")
public class SubGrupoDTO extends EntidadeCodDescDTOBase<SubGrupo> {

	public SubGrupoDTO( SubGrupo entidade ) {

		super(entidade);
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

		return "SubGrupoDTO [" + ( getCodigo() != null ? "getCodigo()=" + getCodigo() + ", " : "" ) + ( getCodigoExtendido() != null ? "getCodigoExtendido()=" + getCodigoExtendido() + ", " : "" ) + ( getDescricao() != null ? "getDescricao()=" + getDescricao() : "" ) + "]";
	}

}
