package com.gleysonsampaio.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class Endereco {

	@NotEmpty
	@NotNull
	private String cep;

	@NotEmpty
	@NotNull
	@Size(max = 30)
	private String logradouro;

	@NotEmpty
	@NotNull
	private String bairro;

	@NotEmpty
	@NotNull
	private String cidade;

	@NotEmpty
	@NotNull
	private String uf;

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

}
