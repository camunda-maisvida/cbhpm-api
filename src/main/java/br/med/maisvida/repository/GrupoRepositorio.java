package br.med.maisvida.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.med.maisvida.entity.Grupo;

@Repository
public interface GrupoRepositorio extends RepositorioCodDescBase<Grupo> {

	@Query("select g from Grupo g join g.capitulo c where c.id = :idCapitulo")
	public List<Grupo> buscarPorCapitulo(@Param("idCapitulo") Long idCapitulo);
}
