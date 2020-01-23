package br.med.maisvida.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import br.med.maisvida.entity.Capitulo;
import br.med.maisvida.entity.Grupo;
import br.med.maisvida.entity.Procedimento;
import br.med.maisvida.entity.SubGrupo;
import br.med.maisvida.entity.Tabela;
import br.med.maisvida.repository.ProcedimentoCustomRepositorio;
import br.med.maisvida.rest.dto.ParametroConsultaProcedimentoDTO;
import br.med.maisvida.rest.dto.ProcedimentoDTO;

public class ProcedimentoCustomRepositorioImpl implements ProcedimentoCustomRepositorio {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Procedimento> buscarPorParametro(ParametroConsultaProcedimentoDTO parametro) {

		List<Procedimento> resultado = new ArrayList<>();
		if (parametro != null) {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Procedimento> query = criteriaBuilder.createQuery(Procedimento.class);
			Root<Procedimento> root = query.from(Procedimento.class);

			List<Predicate> predicates = new ArrayList<>();
			Join<Procedimento,SubGrupo> subGrupoJoin = root.join("subGrupo", JoinType.LEFT);
			Join<SubGrupo,Grupo> grupoJoin = subGrupoJoin.join("grupo", JoinType.LEFT);
			Join<Grupo,Capitulo> capituloJoin = grupoJoin.join("capitulo", JoinType.LEFT);
			Join<Capitulo,Tabela> tabelaJoin = capituloJoin.join("tabela", JoinType.LEFT);

			if (temNumeroPreenchido(parametro.getTabelaId())) {
				predicates.add(criteriaBuilder.and(criteriaBuilder.equal(tabelaJoin.get("id"), parametro.getTabelaId())));
			}

			if (temNumeroPreenchido(parametro.getCapituloId())) {
				predicates.add(criteriaBuilder.and(criteriaBuilder.equal(capituloJoin.get("id"), parametro.getCapituloId())));
			}

			if (temNumeroPreenchido(parametro.getGrupoId())) {
				predicates.add(criteriaBuilder.and(criteriaBuilder.equal(grupoJoin.get("id"), parametro.getGrupoId())));
			}

			if (temNumeroPreenchido(parametro.getSubGrupoId())) {
				predicates.add(criteriaBuilder.and(criteriaBuilder.equal(subGrupoJoin.get("id"), parametro.getSubGrupoId())));
			}

			if (temNumeroPreenchido(parametro.getProcedimentoId())) {
				predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("id"), parametro.getProcedimentoId())));
			}

			if (temTextoPreenchido(parametro.getProcedimentoDescricao())) {
				predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("descricao"), "%"+parametro.getProcedimentoDescricao()+"%")));
			}

			query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));

			resultado = entityManager.createQuery(query).getResultList();
		}
		return resultado;
	}
	
	@Override
	public List<ProcedimentoDTO> buscarDTOPorParametro(ParametroConsultaProcedimentoDTO parametro) {

		List<ProcedimentoDTO> resultado = new ArrayList<>();
		if (parametro != null) {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<ProcedimentoDTO> criteriaQuery = criteriaBuilder.createQuery(ProcedimentoDTO.class);

			Root<Procedimento> root = criteriaQuery.from(Procedimento.class);
			Join<Procedimento,SubGrupo> subGrupoJoin = root.join("subGrupo");
			Join<SubGrupo,Grupo> grupoJoin = subGrupoJoin.join("grupo");
			Join<Grupo,Capitulo> capituloJoin = grupoJoin.join("capitulo");
			Join<Capitulo,Tabela> tabelaJoin = capituloJoin.join("tabela");

			criteriaQuery.select(criteriaBuilder.construct(ProcedimentoDTO.class, 
					root.get("id"), 
					root.get("codigo"), 
					root.get("codigoExtendido"), 
					root.get("descricao"), 
					root.get("porte"), 
					root.get("uco"), 
					root.get("numAuxiliares"), 
					root.get("porteAnest"), 
					tabelaJoin.get("id"), 
					capituloJoin.get("id"), 
					grupoJoin.get("id"), 
					subGrupoJoin.get("id")));
			
			List<Predicate> predicates = new ArrayList<>();
			if (temNumeroPreenchido(parametro.getTabelaId())) {
				predicates.add(criteriaBuilder.and(criteriaBuilder.equal(tabelaJoin.get("id"), parametro.getTabelaId())));
			}

			if (temNumeroPreenchido(parametro.getCapituloId())) {
				predicates.add(criteriaBuilder.and(criteriaBuilder.equal(capituloJoin.get("id"), parametro.getCapituloId())));
			}

			if (temNumeroPreenchido(parametro.getGrupoId())) {
				predicates.add(criteriaBuilder.and(criteriaBuilder.equal(grupoJoin.get("id"), parametro.getGrupoId())));
			}

			if (temNumeroPreenchido(parametro.getSubGrupoId())) {
				predicates.add(criteriaBuilder.and(criteriaBuilder.equal(subGrupoJoin.get("id"), parametro.getSubGrupoId())));
			}

			if (temNumeroPreenchido(parametro.getProcedimentoId())) {
				predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("id"), parametro.getProcedimentoId())));
			}

			if (temTextoPreenchido(parametro.getProcedimentoDescricao())) {
				predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("descricao"), "%"+parametro.getProcedimentoDescricao()+"%")));
			}

			
			criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));

			resultado = entityManager.createQuery(criteriaQuery).getResultList();
		}
		return resultado;
	}

	private boolean temNumeroPreenchido(Number id) {
		
		return id != null && id.intValue() > 0;
	}

	private boolean temTextoPreenchido(String texto) {

		return texto != null && StringUtils.isNotBlank(texto);
	}

}
