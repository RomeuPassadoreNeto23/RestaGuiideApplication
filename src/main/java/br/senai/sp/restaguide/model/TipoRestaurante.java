package br.senai.sp.restaguide.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
public class TipoRestaurante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	@NotEmpty
	private String nome;
	@NotEmpty
	@Column( columnDefinition = "VARCHAR(500)")
	private String descrica;
	@NotEmpty
	@Column( columnDefinition = "VARCHAR(100)")
	private String palavrachave;

}
