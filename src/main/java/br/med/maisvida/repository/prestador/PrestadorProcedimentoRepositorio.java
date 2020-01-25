package br.med.maisvida.repository.prestador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.med.maisvida.entity.prestador.PrestadorProcedimento;
import br.med.maisvida.entity.prestador.PrestadorProcedimentoPK;

@Repository
public interface PrestadorProcedimentoRepositorio extends JpaRepository<PrestadorProcedimento, PrestadorProcedimentoPK> {
	
	@Modifying
	@Query("delete from PrestadorProcedimento pp where pp.prestador.id = :idPrestador")
	public void deletarPorPrestador(Long idPrestador);

	@Modifying
	@Query("delete from PrestadorProcedimento pp where pp.procedimento.id in :idProcedimentos")
	public void deletarPorProcedimentos(Long ... idProcedimentos);

}
