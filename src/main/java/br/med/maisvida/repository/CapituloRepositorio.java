package br.med.maisvida.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.med.maisvida.entity.Capitulo;

@Repository
public interface CapituloRepositorio extends RepositorioBase<Capitulo> {

	public Capitulo findByCodigo(Integer codigo);

	@Query("select c from Capitulo c join c.tabela t where t.id = :idTabela")
	public List<Capitulo> buscarPorTabela(@Param("idTabela") Long idTabela);
}
