package br.senai.sp.restaguide.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.senai.sp.restaguide.model.Usuario;

public interface UsuarioRepositotory extends PagingAndSortingRepository<Usuario, Long> {
	@Query("SELECT u FROM Usuario u WHERE u.email LIKE %:n%")
	public List<Usuario> buscarPeloEmail(@Param("n") String nome);
	@Query("SELECT u FROM Usuario u WHERE u.nome LIKE %:n%")
	public List<Usuario> buscarPeloNome(@Param("n") String nome);
	public Usuario findByEmailAndSenha(String email, String senhs);
}
