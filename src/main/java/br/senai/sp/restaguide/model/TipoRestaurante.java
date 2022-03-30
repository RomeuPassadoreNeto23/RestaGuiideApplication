package br.senai.sp.restaguide.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class TipoRestaurante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	@Column(columnDefinition = "VARCHAR(500)")
	private String descricao;
	@Column(columnDefinition = "VARCHAR(30)")
	private String palavraChave;

}
