package br.med.maisvida.entity.prestador;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.med.maisvida.entity.EntidadeBase;
import br.med.maisvida.entity.Procedimento;

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

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "prestador_procedimentos", schema = "prestador", joinColumns = @JoinColumn(name = "prestador_id", referencedColumnName = "id", nullable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "procedimento_id", referencedColumnName = "id", nullable = false, updatable = false))
	private Set<Procedimento> procedimentos = new HashSet<>();

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
	 * Get the value for <code>procedimentos</code>
	 *
	 * @return <code>Set<Procedimento></code>
	 */
	public Set<Procedimento> getProcedimentos() {

		return procedimentos;
	}

	/**
	 * Set the value for <code>procedimentos</code>.
	 *
	 * @param procedimentos
	 */
	public void setProcedimentos(Set<Procedimento> procedimentos) {

		this.procedimentos = procedimentos;
	}

	/*
	 * public void addProcedimento(Procedimento procedimento) {
	 * 
	 * this.procedimentos.add(procedimento); procedimento.getPrestadores().add(this); }
	 * 
	 * public void removeProcedimento(Procedimento procedimento) {
	 * 
	 * this.procedimentos.remove(procedimento); procedimento.getPrestadores().remove(this); }
	 */

}
