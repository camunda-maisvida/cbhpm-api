package br.med.maisvida.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.med.maisvida.entity.SubGrupo;

@Repository
public interface SubGrupoRepositorio extends RepositorioCodDescBase<SubGrupo> {

	@Query("select sg from SubGrupo sg join sg.grupo g where g.id = :idGrupo")
	public List<SubGrupo> buscarPorGrupo(@Param("idGrupo") Long idGrupo);
}
