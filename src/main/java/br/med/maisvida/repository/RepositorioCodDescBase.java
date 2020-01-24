package br.med.maisvida.repository;

import org.springframework.data.repository.NoRepositoryBean;

import br.med.maisvida.entity.EntidadeCodDescBase;

@NoRepositoryBean
public interface RepositorioCodDescBase<T extends EntidadeCodDescBase> extends RepositorioBase<T> {

	public T findByCodigoExtendido(String codigo);

}
