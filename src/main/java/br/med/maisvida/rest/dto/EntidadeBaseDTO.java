package br.med.maisvida.rest.dto;

import org.springframework.beans.BeanUtils;

import br.med.maisvida.entity.EntidadeBase;

public abstract class EntidadeBaseDTO<T extends EntidadeBase> {

	private Long id;

	public EntidadeBaseDTO() {

	}

	public EntidadeBaseDTO( T entidade ) {

		this.populate(entidade);
	}

	public void populate(T entidade) {

		if (entidade != null) {
			BeanUtils.copyProperties(entidade, this);
		}
	}

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}
}
