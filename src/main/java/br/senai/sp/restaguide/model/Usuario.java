package br.senai.sp.restaguide.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.senai.sp.restaguide.util.HashUtil;
import lombok.Data;

@Entity
@Data
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	private String nome;
	@Column(unique = true)
	@Email
	private String email;
	@NotEmpty
	@JsonProperty(access = Access.WRITE_ONLY)
	private String senha;
	public void setSenha(String senha) {
		//aplica o has e
		this.senha = HashUtil.hash256(senha);
	}
	public void setSenhaComHash(String has) {
		this.senha = has;
	}
	
	

}
