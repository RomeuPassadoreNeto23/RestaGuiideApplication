package br.senai.sp.restaguide.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.senai.sp.restaguide.model.Avaliacao;

public interface AvaliacaoRepository  extends PagingAndSortingRepository<Avaliacao, Long>{
	public List<Avaliacao> findByRestauranteId(Long id);
	
	@Query("SELECT a FROM Avaliacao a WHERE a.comentario LIKE %:n%")
	public List<Avaliacao> buscarPeloComentario(@Param("n") String nome);


}
