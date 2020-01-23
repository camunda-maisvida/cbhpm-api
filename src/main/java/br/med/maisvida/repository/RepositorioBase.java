package br.med.maisvida.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import br.med.maisvida.entity.EntidadeBase;

@NoRepositoryBean
public interface RepositorioBase<T extends EntidadeBase> extends JpaRepository<T, Long> {

}
