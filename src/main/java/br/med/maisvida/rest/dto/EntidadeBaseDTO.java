package br.med.maisvida.rest.dto;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

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
			try {
				BeanUtils.copyProperties(this, entidade);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}
}
