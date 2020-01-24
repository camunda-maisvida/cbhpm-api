package br.med.maisvida.rest.dto;

import com.fasterxml.jackson.annotation.JsonRootName;

import br.med.maisvida.entity.Grupo;

@JsonRootName(value = "grupo")
public class GrupoDTO extends EntidadeCodDescDTOBase<Grupo> {

	public GrupoDTO( Grupo entidade ) {

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

		return "GrupoDTO [" + ( getCodigo() != null ? "getCodigo()=" + getCodigo() + ", " : "" ) + ( getCodigoExtendido() != null ? "getCodigoExtendido()=" + getCodigoExtendido() + ", " : "" ) + ( getDescricao() != null ? "getDescricao()=" + getDescricao() : "" ) + "]";
	}

}
