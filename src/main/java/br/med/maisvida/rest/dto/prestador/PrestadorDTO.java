package br.med.maisvida.rest.dto.prestador;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import br.med.maisvida.entity.prestador.Prestador;
import br.med.maisvida.rest.dto.EntidadeBaseDTO;

@JsonRootName(value = "prestador")
public class PrestadorDTO extends EntidadeBaseDTO<Prestador> {

	@NotNull
	private Long cnpj;

	@NotBlank
	private String codigoCnes;

	@NotBlank
	private String codigoUnidade;

	@NotBlank
	private String nomeFantasia;

	@NotBlank
	private String nomeEmpresarial;

	@Email
	private String email;

	@NotNull
	private LocalDateTime dtAtualizacao;

	@JsonProperty("endereco")
	private EnderecoDTO endereco;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Set<Long> procedimentosId = new HashSet<>();

	public PrestadorDTO() {

	}

	public PrestadorDTO( Prestador entidade ) {

		super(entidade);
		BeanUtils.copyProperties(entidade.getEndereco(), this.getEndereco());
	}

	/**
	 * Get the value for <code>cnpj</code>
	 *
	 * @return <code>Long</code>
	 */
	public Long getCnpj() {

		return cnpj;
	}

	/**
	 * Set the value for <code>cnpj</code>.
	 *
	 * @param cnpj
	 */
	public void setCnpj(Long cnpj) {

		this.cnpj = cnpj;
	}

	/**
	 * Get the value for <code>codigoCnes</code>
	 *
	 * @return <code>String</code>
	 */
	public String getCodigoCnes() {

		return codigoCnes;
	}

	/**
	 * Set the value for <code>codigoCnes</code>.
	 *
	 * @param codigoCnes
	 */
	public void setCodigoCnes(String codigoCnes) {

		this.codigoCnes = codigoCnes;
	}

	/**
	 * Get the value for <code>codigoUnidade</code>
	 *
	 * @return <code>String</code>
	 */
	public String getCodigoUnidade() {

		return codigoUnidade;
	}

	/**
	 * Set the value for <code>codigoUnidade</code>.
	 *
	 * @param codigoUnidade
	 */
	public void setCodigoUnidade(String codigoUnidade) {

		this.codigoUnidade = codigoUnidade;
	}

	/**
	 * Get the value for <code>nomeFantasia</code>
	 *
	 * @return <code>String</code>
	 */
	public String getNomeFantasia() {

		return nomeFantasia;
	}

	/**
	 * Set the value for <code>nomeFantasia</code>.
	 *
	 * @param nomeFantasia
	 */
	public void setNomeFantasia(String nomeFantasia) {

		this.nomeFantasia = nomeFantasia;
	}

	/**
	 * Get the value for <code>nomeEmpresarial</code>
	 *
	 * @return <code>String</code>
	 */
	public String getNomeEmpresarial() {

		return nomeEmpresarial;
	}

	/**
	 * Set the value for <code>nomeEmpresarial</code>.
	 *
	 * @param nomeEmpresarial
	 */
	public void setNomeEmpresarial(String nomeEmpresarial) {

		this.nomeEmpresarial = nomeEmpresarial;
	}

	/**
	 * Get the value for <code>email</code>
	 *
	 * @return <code>String</code>
	 */
	public String getEmail() {

		return email;
	}

	/**
	 * Set the value for <code>email</code>.
	 *
	 * @param email
	 */
	public void setEmail(String email) {

		this.email = email;
	}

	/**
	 * Get the value for <code>dtAtualizacao</code>
	 *
	 * @return <code>LocalDateTime</code>
	 */
	public LocalDateTime getDtAtualizacao() {

		return dtAtualizacao;
	}

	/**
	 * Set the value for <code>dtAtualizacao</code>.
	 *
	 * @param dtAtualizacao
	 */
	public void setDtAtualizacao(LocalDateTime dtAtualizacao) {

		this.dtAtualizacao = dtAtualizacao;
	}

	/**
	 * Get the value for <code>endereco</code>
	 *
	 * @return <code>EnderecoDTO</code>
	 */
	public EnderecoDTO getEndereco() {

		if (this.endereco == null) {
			this.endereco = new EnderecoDTO();
		}
		return endereco;
	}

	/**
	 * Set the value for <code>endereco</code>.
	 *
	 * @param endereco
	 */
	public void setEndereco(EnderecoDTO endereco) {

		this.endereco = endereco;
	}

}
