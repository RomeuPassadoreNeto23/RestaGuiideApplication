package br.senai.sp.restaguide.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import br.senai.sp.restaguide.util.HashUtil;
import lombok.Data;
//para gera get e set
@Data
// para mapear como uma entidade jpa
@Entity
public class Administrador {
	// chave primária e aouto-incremente
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	private String nome;
	@Column(unique = true)
	@Email
	private String email;
	@NotEmpty
	private String senha;
	public void setSenha(String senha) {
		//aplica o has e
		this.senha = HashUtil.hash256(senha);
	}
	public void setSenhaComHash(String has) {
		this.senha = has;
	}
	

}
