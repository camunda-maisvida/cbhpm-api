package br.med.maisvida.service.impl.prestador;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import br.med.maisvida.entity.prestador.Prestador;
import br.med.maisvida.repository.prestador.PrestadorRepositorio;
import br.med.maisvida.rest.dto.prestador.PrestadorDTO;
import br.med.maisvida.rest.dto.prestador.PrestadorProcedimentoDTO;
import br.med.maisvida.rest.dto.prestador.PrestadorResultDTO;
import br.med.maisvida.service.prestador.PrestadorService;

@Service
@Transactional(readOnly = true)
public class PrestadorServiceImpl implements PrestadorService {

	@Autowired
	private PrestadorRepositorio repositorio;

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly = false)
	public PrestadorDTO salvarRetornandoDTO(@Valid PrestadorDTO prestadorParaSalvar) {

		if (prestadorParaSalvar != null) {
			Prestador prestadorParaSalvarOuAtualizar = new Prestador();
			if (prestadorParaSalvar.getId() != null && prestadorParaSalvar.getId() > 0) {
				prestadorParaSalvarOuAtualizar = this.repositorio.findById(prestadorParaSalvar.getId()).orElseThrow(() -> new IllegalStateException("Prestador não encontrado com o id: " + prestadorParaSalvar.getId()));
			}

			BeanUtils.copyProperties(prestadorParaSalvar, prestadorParaSalvarOuAtualizar);
			BeanUtils.copyProperties(prestadorParaSalvar.getEndereco(), prestadorParaSalvarOuAtualizar.getEndereco());
			Prestador prestadorSalvo = this.repositorio.save(prestadorParaSalvarOuAtualizar);
			em.flush();
			em.clear();
			return new PrestadorDTO(this.repositorio.findById(prestadorSalvo.getId()).get());
		}
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public Prestador salvar(Prestador entidade) {

		if (entidade != null) {
			return this.repositorio.save(entidade);
		}
		return null;
	}

	@Override
	public List<Prestador> buscarTodos(Integer page, Integer size) {

		List<Prestador> resultado = new ArrayList<>();

		if (temNumeroValido(page) && temNumeroValido(size)) {

			Pageable paginacao = PageRequest.of(page, size);

			final Page<Prestador> paginaResult = repositorio.findAll(paginacao);

			resultado = paginaResult.getContent();
		} else {
			resultado = repositorio.findAll();
		}
		return resultado;
	}

	@Override
	public PrestadorResultDTO atualizarProcedimentos(PrestadorProcedimentoDTO prestadorProcedimento) {

		PrestadorResultDTO result = null;
		if (prestadorProcedimento != null && temNumeroValido(prestadorProcedimento.getId())) {
			Prestador prestador = this.repositorio.findById(prestadorProcedimento.getId()).orElseThrow(() -> new IllegalStateException("Prestador não encontrado com o id: " + prestadorProcedimento.getId()));
			if (!CollectionUtils.isEmpty(prestadorProcedimento.getProcedimentosSobrepor())) {

			} else {
				if (!CollectionUtils.isEmpty(prestadorProcedimento.getProcedimentosAdicionar())) {

				}
				if (BooleanUtils.isTrue(prestadorProcedimento.getRemoverTodos())) {
					prestador.setProcedimentos(null);
					this.salvar(prestador);
				}
				if (!CollectionUtils.isEmpty(prestadorProcedimento.getProcedimentosRemover())) {

				}
			}

			result = new PrestadorResultDTO(prestador);
		}
		return result;
	}

	@Override
	public Prestador buscarPoID(Long id) {

		return this.repositorio.findById(id).orElse(null);
	}

	// TODO extrair para Utilitario
	private boolean temNumeroValido(Number valor) {

		return valor != null && valor.intValue() >= 0;
	}

}
