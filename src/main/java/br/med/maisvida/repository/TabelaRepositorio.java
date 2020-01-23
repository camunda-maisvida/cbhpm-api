package br.med.maisvida.repository;

import org.springframework.stereotype.Repository;

import br.med.maisvida.entity.Tabela;

@Repository
public interface TabelaRepositorio extends RepositorioBase<Tabela> {

	public Tabela findByCodigo(Integer codigo);
}
