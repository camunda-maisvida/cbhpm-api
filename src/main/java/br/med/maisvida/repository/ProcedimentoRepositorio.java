package br.med.maisvida.repository;

import org.springframework.stereotype.Repository;

import br.med.maisvida.entity.Procedimento;

@Repository
public interface ProcedimentoRepositorio extends RepositorioCodDescBase<Procedimento>, ProcedimentoCustomRepositorio {

}
