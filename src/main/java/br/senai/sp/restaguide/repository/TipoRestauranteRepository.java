package br.senai.sp.restaguide.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.senai.sp.restaguide.model.TipoRestaurante;

public interface TipoRestauranteRepository  extends PagingAndSortingRepository<TipoRestaurante,Long>{
	@Query("SELECT t FROM TipoRestaurante t WHERE t.palavrachave LIKE %:n%")
	public List<TipoRestaurante> buscarPeloPalavrachave(@Param("n") String nome);
	@Query("SELECT t FROM TipoRestaurante t WHERE t.descrica LIKE %:n%")
	public List<TipoRestaurante> buscarPeloDescrica(@Param("n") String nome);
	@Query("SELECT t FROM TipoRestaurante t WHERE t.nome LIKE %:n%")
	public List<TipoRestaurante> buscarPeloNome(@Param("n") String nome);
	public List<TipoRestaurante> findAllByOrderByNomeAsc();

}
