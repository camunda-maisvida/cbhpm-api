package br.med.maisvida.service.impl.prestador;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.med.maisvida.entity.prestador.Prestador;
import br.med.maisvida.repository.prestador.PrestadorRepositorio;
import br.med.maisvida.rest.dto.prestador.PrestadorDTO;
import br.med.maisvida.service.prestador.PrestadorService;

@Service
@Transactional(readOnly = true)
public class PrestadorServiceImpl implements PrestadorService {

	@Autowired
	private PrestadorRepositorio repositorio;

	@Override
	@Transactional(readOnly = false)
	public PrestadorDTO salvarRetornandoDTO(PrestadorDTO prestadorParaSalvar) {

		if (prestadorParaSalvar != null) {
			Prestador prestadorParaSalvarOuAtualizar = new Prestador();
			Long idPrestador = prestadorParaSalvar.getId();
			if (idPrestador != null && idPrestador > 0) {
				prestadorParaSalvarOuAtualizar = this.repositorio.findById(idPrestador).orElse(new Prestador());
			}
			try {
				BeanUtils.copyProperties(prestadorParaSalvarOuAtualizar, prestadorParaSalvar);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			prestadorParaSalvarOuAtualizar.setId(idPrestador);
			Prestador prestadorSalvo = this.repositorio.save(prestadorParaSalvarOuAtualizar);
			return new PrestadorDTO(prestadorSalvo);
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

	// TODO extrair para Utilitario
	private boolean temNumeroValido(Integer valor) {

		return valor != null && valor >= 0;
	}

}
