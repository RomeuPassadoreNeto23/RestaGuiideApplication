package br.senai.sp.restaguide.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.senai.sp.restaguide.model.Avaliacao;

public interface AvaliacaoRepository  extends PagingAndSortingRepository<Avaliacao, Long>{
	public List<Avaliacao> findByRestauranteId(Long id);

}
