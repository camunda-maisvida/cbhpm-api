package br.med.maisvida.repository.prestador;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.med.maisvida.entity.prestador.Prestador;
import br.med.maisvida.repository.RepositorioBase;

@Repository
public interface PrestadorRepositorio extends RepositorioBase<Prestador> {

	Prestador findByCnpj(Long cnpj);
	
	@Modifying
	@Query("update from Prestador p set p.contratoBase64 = :contratoBase64 where p.id = :id")
	public void salvarContrato(Long id, String contratoBase64);


	@Modifying
	@Query("update from Prestador p set p.contratoAssinadoBase64 = :contratoAssinadoBase64 where p.cnpj = :cnpj")
	void salvarContratoAssinado(Long cnpj, String contratoAssinadoBase64);

}
