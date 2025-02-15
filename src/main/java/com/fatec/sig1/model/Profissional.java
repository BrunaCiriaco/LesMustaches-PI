package com.fatec.sig1.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CNPJ;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@Entity
public class Profissional {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "Nome é requerido")
	private String nome;
	@Pattern(regexp = "^(0?[1-9]|[12][0-9]|3[01])[\\/-](0?[1-9]|1[012])[\\/-]\\d{4}$", message = "A data de vencimento deve estar no formato dd/MM/YYYY")
//https://www.regular-expressions.info/
	private String dataNascimento;
	private String dataCadastro;
	private String sexo;
	@CNPJ
	@Column(unique = true) // nao funciona com @Valid tem que tratar na camada de persistencia
	private String cnpj;
	@NotBlank(message = "O CEP é obrigatório.")
	private String cep;
	private String endereco;
	@NotBlank(message = "O complemento deve ser informado")
	private String complemento;

	public Profissional (String nome, String dataNascimento, String sexo, String cnpj, String cep, String complemento) {
		this.nome = nome;
		setDataNascimento(dataNascimento);
		this.sexo = sexo;
		this.cnpj = cnpj;
		this.cep = cep;
		this.complemento = complemento;
	}
	
	public void setDataNascimento(String dataNascimento) {
		if (validaData(dataNascimento) == true) {
			this.dataNascimento = dataNascimento;
		} else {
			throw new IllegalArgumentException("Data invalida");
		}
	}
	public boolean validaData(String data) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false); //
		try {
			df.parse(data); // data válida (exemplo 30 fev - 31 nov)
			return true;
		} catch (ParseException ex) {
			return false;
		}
	}
	public void obtemDataAtual(DateTime dataAtual) {
		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/YYYY");
		this.dataCadastro = dataAtual.toString(fmt);
	}
	public Profissional() {		
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}
}