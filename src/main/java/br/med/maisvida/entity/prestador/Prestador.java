package br.med.maisvida.entity.prestador;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.util.CollectionUtils;

import br.med.maisvida.entity.EntidadeBase;
import br.med.maisvida.entity.Procedimento;
import br.med.maisvida.rest.dto.prestador.PrestadorProcedimentoItemDTO;

@Entity(name = "Prestador")
@Table(name = "prestadores", schema = "prestador")
public class Prestador extends EntidadeBase {

	/** Field serialVersionUID. */
	private static final long serialVersionUID = -7540813438296673725L;

	@NotNull
	@Positive
	@Column(name = "cnpj", nullable = false, updatable = false)
	private Long cnpj;

	@NotBlank
	@Column(name = "codigo_cnes", nullable = false, updatable = false)
	private String codigoCnes;

	@NotBlank
	@Column(name = "codigo_unidade", nullable = false, updatable = false)
	private String codigoUnidade;

	@NotBlank
	@Column(name = "nome_fantasia", nullable = false, updatable = false)
	private String nomeFantasia;

	@NotBlank
	@Column(name = "nome_empresarial", nullable = false, updatable = false)
	private String nomeEmpresarial;

	@Email
	@Column(name = "email")
	private String email;

	@NotNull
	@Column(name = "dt_atualizacao", nullable = false, updatable = false)
	private LocalDateTime dtAtualizacao;

	@Embedded
	private Endereco endereco;

	@OneToMany(mappedBy = "prestador", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH }, orphanRemoval = true)
	private Set<PrestadorProcedimento> prestadorProcedimentos = new HashSet<>();

	@Column(name = "contrato_base64")
	private String contratoBase64;

	@Column(name = "contrato_assinado_base64")
	private String contratoAssinadoBase64;

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
	 * @return <code>Endereco</code>
	 */
	public Endereco getEndereco() {

		if (endereco == null) {
			this.endereco = new Endereco();
		}
		return endereco;
	}

	/**
	 * Set the value for <code>endereco</code>.
	 *
	 * @param endereco
	 */
	public void setEndereco(Endereco endereco) {

		this.endereco = endereco;
	}

	/**
	 * Get the value for <code>prestadorProcedimentos</code>
	 *
	 * @return <code>Set<PrestadorProcedimento></code>
	 */
	public Set<PrestadorProcedimento> getPrestadorProcedimentos() {

		return prestadorProcedimentos;
	}

	/**
	 * Set the value for <code>prestadorProcedimentos</code>.
	 *
	 * @param prestadorProcedimentos
	 */
	public void setPrestadorProcedimentos(Set<PrestadorProcedimento> prestadorProcedimentos) {

		this.prestadorProcedimentos = prestadorProcedimentos;
	}

	/**
	 * Get the value for <code>contratoBase64</code>
	 *
	 * @return <code>String</code>
	 */
	public String getContratoBase64() {

		return contratoBase64;
	}

	/**
	 * Set the value for <code>contratoBase64</code>.
	 *
	 * @param contratoBase64
	 */
	public void setContratoBase64(String contratoBase64) {

		this.contratoBase64 = contratoBase64;
	}

	/**
	 * Get the value for <code>contratoAssinadoBase64</code>
	 *
	 * @return <code>String</code>
	 */
	public String getContratoAssinadoBase64() {

		return contratoAssinadoBase64;
	}

	/**
	 * Set the value for <code>contratoAssinadoBase64</code>.
	 *
	 * @param contratoAssinadoBase64
	 */
	public void setContratoAssinadoBase64(String contratoAssinadoBase64) {

		this.contratoAssinadoBase64 = contratoAssinadoBase64;
	}

	public Set<Procedimento> getProcedimentos() {

		Set<Procedimento> result = new HashSet<>();
		if (!CollectionUtils.isEmpty(this.prestadorProcedimentos)) {

			result = this.prestadorProcedimentos.stream().map(PrestadorProcedimento::getProcedimento).collect(Collectors.toSet());
		}
		return result;
	}

	public void adicionarProcedimentos(Set<PrestadorProcedimentoItemDTO> procedimentosAdicionar) {

		if (!CollectionUtils.isEmpty(procedimentosAdicionar)) {

			Set<Long> idsProcedimentos = this.prestadorProcedimentos.stream().map(m -> m.getProcedimento().getId()).collect(Collectors.toSet());

			procedimentosAdicionar.removeIf(item -> idsProcedimentos.contains(item.getIdProcedimento()));

			Set<PrestadorProcedimento> prestadorProcedimentos = procedimentosAdicionar.stream().map(item -> new PrestadorProcedimento(this, new Procedimento(item.getIdProcedimento()), item.getValor(), item.getValorProposto())).collect(Collectors.toSet());

			this.prestadorProcedimentos.addAll(prestadorProcedimentos);
		}

	}

	public void sobreporProcedimentos(Set<PrestadorProcedimentoItemDTO> procedimentosSobrepor) {

		if (!CollectionUtils.isEmpty(procedimentosSobrepor)) {

			Set<PrestadorProcedimento> prestadorProcedimentos = procedimentosSobrepor.stream().map(item -> new PrestadorProcedimento(this, new Procedimento(item.getIdProcedimento()), item.getValor(), item.getValorProposto())).collect(Collectors.toSet());
			this.prestadorProcedimentos.clear();
			this.prestadorProcedimentos.addAll(prestadorProcedimentos);
		}

	}

	/**
	 * Default description: <br>
	 * <br>
	 *
	 * {@inheritDoc}
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( cnpj == null ) ? 0 : cnpj.hashCode() );
		return result;
	}

	/**
	 * Default description: <br>
	 * <br>
	 *
	 * {@inheritDoc}
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prestador other = (Prestador) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		return true;
	}

}
