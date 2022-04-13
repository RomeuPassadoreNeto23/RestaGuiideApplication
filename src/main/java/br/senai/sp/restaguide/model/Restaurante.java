package br.senai.sp.restaguide.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Restaurante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@Column(columnDefinition = "TEXT")
	private String descricao;
	private String cep;
	private String endereco;
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String complemento;
	@Column(columnDefinition = "TEXT")
	private String fotos;
	@ManyToOne
	private TipoRestaurante tipo;
	private boolean espacoInfantil;
	private boolean estacionamento;
	private boolean driveThru;
	private boolean mamobrista;
	private boolean delivery;
	private boolean atracoes;
	private String fromasPagamento;
	private String site;
	private String telefone;
	private String redesSocial;
	public String[] verFotos(){
		return this.fotos.split(";");
		
	}
}
