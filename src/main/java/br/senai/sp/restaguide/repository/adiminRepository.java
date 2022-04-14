package br.senai.sp.restaguide.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.senai.sp.restaguide.model.Administrador;

public interface adiminRepository  extends PagingAndSortingRepository<Administrador, Long>{
	@Query("SELECT a FROM Administrador a WHERE a.email LIKE %:n%")
	public List<Administrador> buscarPeloEmail(@Param("n") String nome);
	@Query("SELECT a FROM Administrador a WHERE a.nome LIKE %:n%")
	public List<Administrador> buscarPeloNome(@Param("n") String nome);
	public Administrador findByEmailAndSenha(String email, String senhs);

}
